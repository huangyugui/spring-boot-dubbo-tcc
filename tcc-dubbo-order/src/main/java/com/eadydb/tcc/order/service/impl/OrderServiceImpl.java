package com.eadydb.tcc.order.service.impl;

import com.eadydb.tcc.annotation.ReadDataSource;
import com.eadydb.tcc.annotation.WriteDataSource;
import com.eadydb.tcc.order.entity.Order;
import com.eadydb.tcc.order.factory.OrderFactory;
import com.eadydb.tcc.order.repository.OrderRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author eadydb
 * @date 2017-12-04 15:59
 * <p/>
 * @description
 */
@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderFactory orderFactory;

    @WriteDataSource
    @Transactional
    public Order createOrder(long payerUserId, long payeeUserId, List<Pair<Long, Integer>> productQuantities) {
        Order order = orderFactory.buildOrder(payerUserId, payeeUserId, productQuantities);

        orderRepository.createOrder(order);

        return order;
    }

    @ReadDataSource
    public Order findOrderByMerchantOrderNo(String orderNo) {
        return orderRepository.findByMerchantOrderNo(orderNo);
    }
}
