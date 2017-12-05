package com.eadydb.tcc.capital.repository;

import com.eadydb.tcc.capital.entity.CapitalAccount;
import com.eadydb.tcc.capital.mapper.CapitalAccountMapper;
import com.eadydb.tcc.exception.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author eadydb
 * @date 2017-12-04 14:19
 * <p/>
 * @description
 */
@Repository
public class CapitalAccountRepository {

    @Autowired
    CapitalAccountMapper capitalAccountDao;

    public CapitalAccount findByUserId(long userId) {

        return capitalAccountDao.findByUserId(userId);
    }

    public void save(CapitalAccount capitalAccount) {
        int effectCount = capitalAccountDao.update(capitalAccount);
        if (effectCount < 1) {
            throw new InsufficientBalanceException();
        }
    }
}
