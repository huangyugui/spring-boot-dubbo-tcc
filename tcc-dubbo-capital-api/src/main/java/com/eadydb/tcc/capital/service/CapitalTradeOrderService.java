package com.eadydb.tcc.capital.service;

import com.eadydb.tcc.capital.dto.CapitalTradeOrderDto;
import org.mengyun.tcctransaction.api.Compensable;

/**
 * @author eadydb
 * @date 2017-12-04 10:56
 * <p/>
 * @description
 */
public interface CapitalTradeOrderService {

    @Compensable
    String record(CapitalTradeOrderDto tradeOrderDto);

}
