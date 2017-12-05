package com.eadydb.tcc.order.service.impl;

import com.eadydb.tcc.annotation.ReadDataSource;
import com.eadydb.tcc.capital.service.CapitalAccountService;
import com.eadydb.tcc.redpacket.service.RedPacketAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 16:02
 * <p/>
 * @description
 */
@Service("accountService")
public class AccountServiceImpl {

    @Autowired
    RedPacketAccountService redPacketAccountService;
    @Autowired
    CapitalAccountService capitalAccountService;

    @ReadDataSource
    public BigDecimal getRedPacketAccountByUserId(long userId) {
        return redPacketAccountService.getRedPacketAccountByUserId(userId);
    }

    @ReadDataSource
    public BigDecimal getCapitalAccountByUserId(long userId) {
        return capitalAccountService.getCapitalAccountByUserId(userId);
    }
}
