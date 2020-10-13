package cn.edu.dgut.integration.storage.controller;

import cn.edu.dgut.integration.api.StorageApi;
import cn.edu.dgut.integration.common.util.JsonResult;
import cn.edu.dgut.integration.storage.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @Resource
    private StorageApi storageApi;


    @GetMapping("/test")
    public JsonResult test() {
        return JsonResult.ok(storageService.selectById(1L));
    }

    @RequestMapping("/reduce")
    public JsonResult reduce( Long storageId,Long count){
        return JsonResult.ok(storageApi.reduce(storageId, count));
    }

}
