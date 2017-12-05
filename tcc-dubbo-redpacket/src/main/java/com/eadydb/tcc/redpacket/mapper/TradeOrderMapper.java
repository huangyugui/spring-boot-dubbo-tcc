package com.eadydb.tcc.redpacket.mapper;

import com.eadydb.tcc.redpacket.entity.TradeOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eadydb
 * @date 2017-12-04 14:26
 * <p/>
 * @description
 */
@Mapper
public interface TradeOrderMapper {

    void insert(TradeOrder tradeOrder);

    int update(TradeOrder tradeOrder);

    TradeOrder findByMerchantOrderNo(String merchantOrderNo);
}
