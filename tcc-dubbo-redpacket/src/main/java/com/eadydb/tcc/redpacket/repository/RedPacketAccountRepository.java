package com.eadydb.tcc.redpacket.repository;

import com.eadydb.tcc.exception.InsufficientBalanceException;
import com.eadydb.tcc.redpacket.entity.RedPacketAccount;
import com.eadydb.tcc.redpacket.mapper.RedPacketAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author eadydb
 * @date 2017-12-04 14:38
 * <p/>
 * @description
 */
@Repository
public class RedPacketAccountRepository {

    @Autowired
    RedPacketAccountMapper redPacketAccountDao;

    public RedPacketAccount findByUserId(long userId) {

        return redPacketAccountDao.findByUserId(userId);
    }

    public void save(RedPacketAccount redPacketAccount) {
        int effectCount = redPacketAccountDao.update(redPacketAccount);
        if (effectCount < 1) {
            throw new InsufficientBalanceException();
        }
    }
}
