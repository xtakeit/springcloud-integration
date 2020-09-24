package cn.edu.dgut.integration.api;

import cn.edu.dgut.integration.model.Order;

public interface OrderApi {

    Boolean add(Order entity);

    Boolean judgeIsOrdered(Long userId, Long storageId);

}
