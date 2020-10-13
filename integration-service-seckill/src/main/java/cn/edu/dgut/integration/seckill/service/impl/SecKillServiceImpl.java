package cn.edu.dgut.integration.seckill.service.impl;

import cn.edu.dgut.integration.api.OrderApi;
import cn.edu.dgut.integration.api.StorageApi;
import cn.edu.dgut.integration.common.constant.OrderStatusConstant;
import cn.edu.dgut.integration.common.util.JsonResult;
import cn.edu.dgut.integration.model.Order;
import cn.edu.dgut.integration.model.Storage;
import cn.edu.dgut.integration.seckill.service.SecKillService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author moon
 * 不开放dubbo调用
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Reference
    private OrderApi orderApi;

    /*@Reference
    private AccountApi accountApi;*/

    @Reference
    private StorageApi storageApi;


    @GlobalTransactional
    @Override
    public JsonResult tryKill(Long userId, Long storageId) {

        // 判读是否已经下单
        Boolean isOrdered = orderApi.judgeIsOrdered(userId, storageId);
        if (isOrdered) {
            return JsonResult.build(10001, "你已参与秒杀,请不要重复点击", null);
        }
        // 扣减库存
        Boolean reduced = storageApi.reduce(storageId, 1L);
        if (!reduced) {
            return JsonResult.build(10002, "库存不足,秒杀失败", null);
        }
        // 获取商品（库存）的基本信息
        // 生成订单
        Storage storage = storageApi.selectByStorageId(storageId);
        Order order = new Order();
        order.setUserId(userId);
        order.setStorageId(storageId);
        order.setCount(1L);
        order.setStatus(OrderStatusConstant.WAIT_TO_PAY);
        order.setMoney(storage.getMoney());
        Boolean addOrderSuccess = orderApi.add(order);
        if (!addOrderSuccess) {
            return JsonResult.build(10003, "未知原因，下订单失败", null);
        }
        // 生成订单成功
        return JsonResult.build(200, "秒杀成功", null);
    }

}
