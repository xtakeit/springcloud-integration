package cn.edu.dgut.integration.account.service.impl;

import cn.edu.dgut.integration.account.dao.AccountDao;
import cn.edu.dgut.integration.account.service.AccountService;
import cn.edu.dgut.integration.api.AccountApi;
import cn.edu.dgut.integration.common.service.impl.BaseServiceImpl;
import cn.edu.dgut.integration.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author moon
 * dubbo service
 */
@Slf4j
@Service(interfaceClass = AccountApi.class)
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService, AccountApi {

    private AccountDao accountDao;

    @Resource
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
        super.setBaseMapper(accountDao);
    }

    @Override
    public Boolean reduce(Long userId, Double money) {
        // 扣减该用户账户金额
        Integer row = accountDao.reduceMoney(userId, money);
        return judgeRowNotEqualsToZero(row);
    }

    @Override
    public Boolean add(Long userId, Double money) {
        // 增加该用户的账户金额
        Integer row = accountDao.addMoney(userId, money);
        return judgeRowNotEqualsToZero(row);
    }


}
