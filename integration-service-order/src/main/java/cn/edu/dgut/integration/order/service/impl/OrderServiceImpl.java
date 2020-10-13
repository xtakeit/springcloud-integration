package cn.edu.dgut.integration.order.service.impl;

import cn.edu.dgut.integration.api.OrderApi;
import cn.edu.dgut.integration.common.service.impl.BaseServiceImpl;
import cn.edu.dgut.integration.model.Order;
import cn.edu.dgut.integration.order.dao.OrderDao;
import cn.edu.dgut.integration.order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;


@Slf4j
@Service(interfaceClass = OrderApi.class)
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService, OrderApi {


    private OrderDao orderDao;

    @Resource
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
        super.setBaseMapper(orderDao);
    }


    @Override
    public Boolean add(Order entity) {
        Integer row = orderDao.insert(entity);
        System.out.println("row:" + row);
        return judgeRowNotEqualsToZero(row);
    }

    @Override
    public Boolean judgeIsOrdered(Long userId, Long storageId) {
        // 查询订单是否已存在
        Order order = new Order();
        order.setUserId(userId);
        order.setStorageId(storageId);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>(order);
        order = orderDao.selectOne(queryWrapper);
        if (order == null) {
            return false;
        }
        return true;
    }
}
