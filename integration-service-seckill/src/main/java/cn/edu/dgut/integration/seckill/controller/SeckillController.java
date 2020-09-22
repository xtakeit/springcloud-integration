package cn.edu.dgut.integration.seckill.controller;

import cn.edu.dgut.integration.common.util.JsonResult;
import cn.edu.dgut.integration.seckill.service.SeckillService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

@RestController
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Resource
    private SeckillService seckillService;

    @RequestMapping("/kill")
    public JsonResult seckill(@SessionAttribute Long userId, Long StorageId) {
        return null;
    }


}
