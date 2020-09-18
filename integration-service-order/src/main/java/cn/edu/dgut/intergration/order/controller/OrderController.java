package cn.edu.dgut.intergration.order.controller;

import cn.edu.dgut.intergration.api.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {


    @Resource
    private OrderService orderService;

    @RequestMapping("/call")
    public Boolean call(){
        return orderService.call();
    }




}
