package com.eadydb.tcc.order.service.impl;

import com.eadydb.tcc.annotation.ReadDataSource;
import com.eadydb.tcc.order.entity.Product;
import com.eadydb.tcc.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eadydb
 * @date 2017-12-05 09:33
 * <p/>
 * @description
 */
@Service
public class ProductServiceImpl {

    @Autowired
    ProductRepository productRepository;

    @ReadDataSource
    public Product findById(long productId){
        return productRepository.findById(productId);
    }

    @ReadDataSource
    public List<Product> findByShopId(long shopId){
        return productRepository.findByShopId(shopId);
    }
}
