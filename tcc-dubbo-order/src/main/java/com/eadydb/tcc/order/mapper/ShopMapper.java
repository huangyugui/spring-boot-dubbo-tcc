package com.eadydb.tcc.order.mapper;

import com.eadydb.tcc.order.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eadydb
 * @date 2017-12-04 15:42
 * <p/>
 * @description
 */
@Mapper
public interface ShopMapper {
    Shop findById(long id);
}
