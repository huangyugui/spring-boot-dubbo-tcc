package com.eadydb.tcc.order.repository;

import com.eadydb.tcc.order.entity.Product;
import com.eadydb.tcc.order.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author eadydb
 * @date 2017-12-04 15:56
 * <p/>
 * @description
 */
@Repository
public class ProductRepository {

    @Autowired
    ProductMapper productDao;

    public Product findById(long productId){
        return productDao.findById(productId);
    }

    public List<Product> findByShopId(long shopId){
        return productDao.findByShopId(shopId);
    }
}
