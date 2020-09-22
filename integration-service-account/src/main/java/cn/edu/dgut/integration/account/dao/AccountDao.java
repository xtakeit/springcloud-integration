package cn.edu.dgut.integration.account.dao;

import cn.edu.dgut.integration.model.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountDao extends BaseMapper<Account> {
}
