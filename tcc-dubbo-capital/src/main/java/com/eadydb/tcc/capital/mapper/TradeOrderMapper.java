package com.eadydb.tcc.capital.mapper;

import com.eadydb.tcc.capital.entity.TradeOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eadydb
 * @date 2017-12-04 11:42
 * <p/>
 * @description
 */
@Mapper
public interface TradeOrderMapper {

    int insert(TradeOrder order);

    int update(TradeOrder order);

    TradeOrder findByMerchantOrderNo(String merchantOrderNo);
}
