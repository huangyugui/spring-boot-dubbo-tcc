package com.eadydb.tcc.order.repository;

import com.eadydb.tcc.order.entity.Order;
import com.eadydb.tcc.order.entity.OrderLine;
import com.eadydb.tcc.order.mapper.OrderLineMapper;
import com.eadydb.tcc.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

/**
 * @author eadydb
 * @date 2017-12-04 15:55
 * <p/>
 * @description
 */
@Repository
public class OrderRepository {

    @Autowired
    OrderMapper orderDao;

    @Autowired
    OrderLineMapper orderLineDao;

    public void createOrder(Order order) {
        orderDao.insert(order);

        for (OrderLine orderLine : order.getOrderLines()) {
            orderLineDao.insert(orderLine);
        }
    }

    public void updateOrder(Order order) {
        order.updateVersion();
        int effectCount = orderDao.update(order);

        if (effectCount < 1) {
            throw new OptimisticLockingFailureException("update order failed");
        }
    }

    public Order findByMerchantOrderNo(String merchantOrderNo) {
        return orderDao.findByMerchantOrderNo(merchantOrderNo);
    }
}
