package com.eadydb.tcc.capital.service.impl;

import com.eadydb.tcc.annotation.ReadDataSource;
import com.eadydb.tcc.capital.repository.CapitalAccountRepository;
import com.eadydb.tcc.capital.service.CapitalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 11:56
 * <p/>
 * @description
 */
@Service("capitalAccountService")
public class CapitalAccountServiceImpl implements CapitalAccountService {

    @Autowired
    private CapitalAccountRepository capitalAccountRepository;

    @ReadDataSource
    @Override
    public BigDecimal getCapitalAccountByUserId(long userId) {
        return capitalAccountRepository.findByUserId(userId).getBalanceAmount();
    }
}
