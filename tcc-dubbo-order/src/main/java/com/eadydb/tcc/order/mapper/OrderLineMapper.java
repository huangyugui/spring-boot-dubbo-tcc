package com.eadydb.tcc.order.mapper;

import com.eadydb.tcc.order.entity.OrderLine;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eadydb
 * @date 2017-12-04 15:38
 * <p/>
 * @description
 */
@Mapper
public interface OrderLineMapper {

    void insert(OrderLine orderLine);
}
