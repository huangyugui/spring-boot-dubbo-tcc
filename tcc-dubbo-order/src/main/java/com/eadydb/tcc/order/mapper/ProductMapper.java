package com.eadydb.tcc.order.mapper;

import com.eadydb.tcc.order.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author eadydb
 * @date 2017-12-04 15:42
 * <p/>
 * @description
 */
@Mapper
public interface ProductMapper {

    Product findById(long productId);

    List<Product> findByShopId(long shopId);
}
