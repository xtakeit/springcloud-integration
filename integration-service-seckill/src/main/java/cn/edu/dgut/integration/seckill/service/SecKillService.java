package cn.edu.dgut.integration.seckill.service;

import cn.edu.dgut.integration.common.util.JsonResult;

public interface SecKillService {

    /**
     * 操作数据库(版本1)
     * @param userId
     * @param storageId
     * @return
     */
    JsonResult tryKill(Long userId, Long storageId);


}
