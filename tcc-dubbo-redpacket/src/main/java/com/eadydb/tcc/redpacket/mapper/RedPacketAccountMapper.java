package com.eadydb.tcc.redpacket.mapper;

import com.eadydb.tcc.redpacket.entity.RedPacketAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eadydb
 * @date 2017-12-04 14:25
 * <p/>
 * @description
 */
@Mapper
public interface RedPacketAccountMapper {

    RedPacketAccount findByUserId(long userId);

    int update(RedPacketAccount redPacketAccount);
}
