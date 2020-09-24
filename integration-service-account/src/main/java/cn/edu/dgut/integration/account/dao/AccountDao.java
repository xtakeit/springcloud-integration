package cn.edu.dgut.integration.account.dao;

import cn.edu.dgut.integration.model.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountDao extends BaseMapper<Account> {


    @Update("update account_tbl set money=money-#{reduceMoney} where user_id = #{userId} and (money-#{reduceMoney}) > 0 ")
    int reduceMoney(@Param("userId") Long userId, @Param("reduceMoney") Double reduceMoney);

    @Update("update account_tbl set money=money-#{addMoney} where user_id = #{userId} and (money-#{addMoney}) > 0 ")
    int addMoney(@Param("userId") Long userId, @Param("addMoney") Double addMoney);

}
