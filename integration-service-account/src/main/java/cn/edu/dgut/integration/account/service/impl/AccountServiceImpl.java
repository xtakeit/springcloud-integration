package cn.edu.dgut.integration.account.service.impl;

import cn.edu.dgut.integration.api.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author moon
 * dubbo service
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Boolean accountCall() {
        log.info("==accountCall==");
        return true;
    }

}
