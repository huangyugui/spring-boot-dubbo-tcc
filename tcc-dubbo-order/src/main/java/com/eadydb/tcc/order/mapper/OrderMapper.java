package com.eadydb.tcc.order.mapper;

import com.eadydb.tcc.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eadydb
 * @date 2017-12-04 15:33
 * <p/>
 * @description
 */
@Mapper
public interface OrderMapper {

    int insert(Order order);

    int update(Order order);

    Order findByMerchantOrderNo(String merchantOrderNo);
}
