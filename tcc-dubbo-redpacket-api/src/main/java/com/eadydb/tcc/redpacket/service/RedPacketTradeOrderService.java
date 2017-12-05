package com.eadydb.tcc.redpacket.service;

import com.eadydb.tcc.redpacket.dto.RedPacketTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

/**
 * @author eadydb
 * @date 2017-12-04 11:19
 * <p/>
 * @description
 */
public interface RedPacketTradeOrderService {

    @Compensable
    String record(RedPacketTradeOrderDto tradeOrderDto);
}
