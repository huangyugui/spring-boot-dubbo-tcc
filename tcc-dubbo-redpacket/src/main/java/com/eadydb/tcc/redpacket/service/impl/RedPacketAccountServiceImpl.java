package com.eadydb.tcc.redpacket.service.impl;

import com.eadydb.tcc.annotation.ReadDataSource;
import com.eadydb.tcc.redpacket.repository.RedPacketAccountRepository;
import com.eadydb.tcc.redpacket.service.RedPacketAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 14:40
 * <p/>
 * @description
 */
@Service("redPacketAccountService")
public class RedPacketAccountServiceImpl implements RedPacketAccountService {

    @Autowired
    private RedPacketAccountRepository redPacketAccountRepository;

    @ReadDataSource
    @Override
    public BigDecimal getRedPacketAccountByUserId(long userId) {
        return redPacketAccountRepository.findByUserId(userId).getBalanceAmount();
    }
}
