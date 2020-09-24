package cn.edu.dgut.integration.seckill.service;

import cn.edu.dgut.integration.common.util.JsonResult;

public interface SecKillService {

    /**
     * 不操作数据库
     * @param userId
     * @param storageId
     * @return
     */
    JsonResult tryKill(Long userId, Long storageId);

}
