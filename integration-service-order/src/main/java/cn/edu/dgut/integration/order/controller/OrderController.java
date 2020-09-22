package cn.edu.dgut.integration.order.controller;

import cn.edu.dgut.integration.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {


    @Resource
    private OrderService orderService;

    @SentinelResource(value = "order-call", blockHandler = "blockExceptionHandler", fallback = "fallbackExceptionHandler")
    @RequestMapping("/call")
    public Boolean call() {
        return true;
    }

    public Boolean blockExceptionHandler(BlockException ex) {
        System.out.println("---block-loading---");
        return false;
    }

    public Boolean fallbackExceptionHandler() {
        System.out.println("---fallback-loading---");
        return false;
    }


}
