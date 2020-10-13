package cn.edu.dgut.integration.seckill.service;

import cn.edu.dgut.integration.common.util.Result;

public interface SecKill2Service {


    String CONSUMED_KEY = "consumed:";
    String INVOKED_KEY = "invoked:";
    String STORAGE_KEY = "storage:";
    String STORAGE_COUNT_KEY = "storage:count:";


    /**
     *  不操作数据库(版本2)
     * @param userId
     * @param storageId
     * @return
     */
    Result tryKill(Long userId, Long storageId);

    /**
     *  下单，操作数据库
     * @param userId
     * @param storageId
     */
    void kill(Long userId, Long storageId);


    /**
     * 缓存所有的库存以及状态
     */
    void cacheStorage();

    /**
     * 查询数据库中是否已存在订单
     * @param userId
     * @param storageId
     * @return
     */
    Boolean checkResult(Long userId,Long storageId);


    /**
     * 库存回退（取消订单、超时未支付）
     * @param userId
     * @param storageId
     */
    void rollbackStorage(Long userId,Long storageId);
}
