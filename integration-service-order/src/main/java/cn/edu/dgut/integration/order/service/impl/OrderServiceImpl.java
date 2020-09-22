package cn.edu.dgut.integration.order.service.impl;

import cn.edu.dgut.integration.api.OrderApi;
import cn.edu.dgut.integration.common.service.impl.BaseServiceImpl;
import cn.edu.dgut.integration.model.Order;
import cn.edu.dgut.integration.order.dao.OrderDao;
import cn.edu.dgut.integration.order.service.OrderService;
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

}
