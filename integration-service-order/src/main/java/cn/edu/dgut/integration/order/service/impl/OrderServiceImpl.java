package cn.edu.dgut.integration.order.service.impl;

import cn.edu.dgut.integration.api.AccountService;
import cn.edu.dgut.integration.api.OrderService;
import cn.edu.dgut.integration.api.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author moon
 * 不开放dubbo给别人调用
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Reference
    private AccountService accountService;

    @Reference
    private StorageService storageService;

    @Override
    public Boolean call() {
        log.info("调用结果:" + accountService.accountCall() + ":" + storageService.storageCall());
        return true;
    }

}
