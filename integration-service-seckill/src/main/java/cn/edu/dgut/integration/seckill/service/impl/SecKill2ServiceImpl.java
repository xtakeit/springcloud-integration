package cn.edu.dgut.integration.seckill.service.impl;


import cn.edu.dgut.integration.api.OrderApi;
import cn.edu.dgut.integration.api.StorageApi;
import cn.edu.dgut.integration.common.constant.OrderStatusConstant;
import cn.edu.dgut.integration.common.util.Result;
import cn.edu.dgut.integration.common.util.ResultCode;
import cn.edu.dgut.integration.model.Order;
import cn.edu.dgut.integration.model.Storage;
import cn.edu.dgut.integration.seckill.redis.BaseRedis;
import cn.edu.dgut.integration.seckill.service.SecKill2Service;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author moon
 * 不开放dubbo调用
 */
@Slf4j
@Service
public class SecKill2ServiceImpl implements SecKill2Service {

    @Reference(timeout = 1200000)
    private OrderApi orderApi;

    /*@Reference
    private AccountApi accountApi;*/

    @Reference(timeout = 1200000)
    private StorageApi storageApi;

    @Resource
    private BaseRedis baseRedis;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    // 在本地存储是否还有库存
    private final Map<String, Boolean> hasStocks = new HashMap<>();

    @Override
    public Result tryKill(Long userId, Long storageId) {
        String baseKey = userId + ":" + storageId;
        // 本地判断是否有库存
        if (!hasStocks.get(storageId.toString())) {
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        // 添加锁，防止一个用户多次参与
        RLock lock = redissonClient.getLock(baseKey);
        try {
            boolean result = lock.tryLock(-1, 10, TimeUnit.SECONDS);
            System.out.println("lock result:" + result);
            // 当进入秒杀时，判断是否已参与(未处理)
            if (baseRedis.hasKey(CONSUMED_KEY + baseKey)) {
                return Result.failure(ResultCode.SECKILL_LINE_UP);
            }
            // 设置参与缓存为30秒，防止当时没有抢到，后面库存回退无法再次抢购
            baseRedis.set(CONSUMED_KEY + baseKey, false, 10 * 60);
        } catch (Exception e) {
            System.out.println("===fail lock===");
            return Result.failure(ResultCode.FAIL);
        } finally {
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        // 预减库存,小于0,设置已经没有库存
        if (baseRedis.decr(STORAGE_COUNT_KEY + storageId, 1) < 0) {
            hasStocks.put(storageId.toString(), false);
            baseRedis.incr(STORAGE_COUNT_KEY + storageId, 1);
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        // 放入消息队列中,返回需要排队消息
        kafkaTemplate.send("secKill2", baseKey, baseKey);
        return Result.failure(ResultCode.SECKILL_LINE_UP);

    }

    @Override
    @GlobalTransactional
    public void kill(Long userId, Long storageId) {
        String baseKey = userId + ":" + storageId;
        // 保证消息幂等性
        Boolean isConsumed = (Boolean) baseRedis.get(CONSUMED_KEY + baseKey);
        if (isConsumed == null || isConsumed) {
            System.out.println("redutn" + isConsumed);
            return;
        }
        // 如果已经购买,不再允许
        if (orderApi.judgeIsOrdered(userId, storageId)) {
            System.out.println("isOrder");
            baseRedis.set(CONSUMED_KEY + baseKey, true);
            return;
        }
        // 扣减库存
        Boolean reduced = storageApi.reduce(storageId, 1L);
        if (!reduced) {
            return;
        }
        // 生成订单
        Storage storage = storageApi.selectByStorageId(storageId);
        Order order = new Order();
        order.setUserId(userId);
        order.setStorageId(storageId);
        order.setCount(1L);
        order.setStatus(OrderStatusConstant.WAIT_TO_PAY);
        order.setMoney(storage.getMoney());
        orderApi.add(order);
        // 消费成功，设置已消费
        baseRedis.set(CONSUMED_KEY + baseKey, true);
    }

    @Override
    public void cacheStorage() {
        List<Storage> storageList = storageApi.selectAll();
        // 缓存数据，以及初始化本地库存状态为有货
        storageList.parallelStream().forEach(storage -> {
            baseRedis.set(STORAGE_KEY + storage.getStorageId(), storage);
            baseRedis.set(STORAGE_COUNT_KEY + storage.getStorageId(), storage.getCount().intValue());
            hasStocks.put(storage.getStorageId().toString(), true);
        });
    }

    @Override
    public Boolean checkResult(Long userId, Long storageId) {
        return orderApi.judgeIsOrdered(userId, storageId);
    }


    @Override
    public void rollbackStorage(Long userId, Long storageId) {
        // TODO 增加数据库中的库存
        // TODO 增加redis种的库存
        // TODO 修改库存状态位
    }

}
