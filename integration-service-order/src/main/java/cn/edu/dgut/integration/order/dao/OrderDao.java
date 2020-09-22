package cn.edu.dgut.integration.order.dao;

import cn.edu.dgut.integration.model.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDao extends BaseMapper<Order> {
}
