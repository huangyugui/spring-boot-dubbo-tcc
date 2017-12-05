package com.eadydb.tcc.order.repository;

import com.eadydb.tcc.order.entity.Shop;
import com.eadydb.tcc.order.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author eadydb
 * @date 2017-12-04 15:57
 * <p/>
 * @description
 */
@Repository
public class ShopRepository {

    @Autowired
    ShopMapper shopDao;

    public Shop findById(long id) {

        return shopDao.findById(id);
    }
}
