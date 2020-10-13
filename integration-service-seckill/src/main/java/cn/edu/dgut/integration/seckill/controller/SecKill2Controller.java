package cn.edu.dgut.integration.seckill.controller;


import cn.edu.dgut.integration.common.util.Result;
import cn.edu.dgut.integration.common.util.ResultCode;
import cn.edu.dgut.integration.seckill.service.SecKill2Service;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/secKill2")
public class SecKill2Controller implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        seckill2Service.cacheStorage();
    }

    @Resource
    private SecKill2Service seckill2Service;

    /**
     * 第二版秒杀，redis缓存，kafka异步处理
     *
     * @param userId
     * @param storageId
     * @return
     */
    @RequestMapping("/kill")
    public Result secKill2(Long userId, Long storageId) {
        return seckill2Service.tryKill(userId, storageId);
    }

    /**
     * 第二版秒杀，查询结果接口
     *
     * @param userId
     * @param storageId
     * @return
     */
    @RequestMapping("/result")
    public Result result(Long userId, Long storageId) {
        Boolean success = seckill2Service.checkResult(userId, storageId);
        if (success) {
            return Result.success();
        }
        return Result.failure(ResultCode.SECKILL_LINE_UP);
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 1; i < 20000; i++) {
            System.out.println(i + "," + (random.nextInt(10) + 1));
        }
    }

}
