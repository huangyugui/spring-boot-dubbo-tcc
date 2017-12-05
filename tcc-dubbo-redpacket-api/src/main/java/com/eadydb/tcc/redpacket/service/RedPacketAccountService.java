package com.eadydb.tcc.redpacket.service;

import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 11:19
 * <p/>
 * @description
 */
public interface RedPacketAccountService {

    BigDecimal getRedPacketAccountByUserId(long userId);
}
