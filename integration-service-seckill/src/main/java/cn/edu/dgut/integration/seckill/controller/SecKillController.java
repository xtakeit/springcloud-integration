package cn.edu.dgut.integration.seckill.controller;

import cn.edu.dgut.integration.common.util.JsonResult;
import cn.edu.dgut.integration.seckill.service.SecKillService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/secKill")
public class SecKillController {

    @Resource
    private SecKillService seckillService;

    /**
     * 第一版秒杀，同步调用返回
     * @param userId
     * @param storageId
     * @return
     */
    @RequestMapping("/kill")
    public JsonResult secKill(Long userId, Long storageId) {
        return seckillService.tryKill(userId, storageId);

    }

}
