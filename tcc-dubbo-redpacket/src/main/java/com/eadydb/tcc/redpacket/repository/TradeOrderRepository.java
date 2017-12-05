package com.eadydb.tcc.redpacket.repository;

import com.eadydb.tcc.redpacket.entity.TradeOrder;
import com.eadydb.tcc.redpacket.mapper.TradeOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

/**
 * @author eadydb
 * @date 2017-12-04 14:39
 * <p/>
 * @description
 */
@Repository
public class TradeOrderRepository {

    @Autowired
    TradeOrderMapper tradeOrderDao;

    public void insert(TradeOrder tradeOrder) {
        tradeOrderDao.insert(tradeOrder);
    }

    public void update(TradeOrder tradeOrder) {

        tradeOrder.updateVersion();
        int effectCount = tradeOrderDao.update(tradeOrder);
        if (effectCount < 1) {
            throw new OptimisticLockingFailureException("update trade order failed");
        }
    }

    public TradeOrder findByMerchantOrderNo(String merchantOrderNo) {
        return tradeOrderDao.findByMerchantOrderNo(merchantOrderNo);
    }
}
