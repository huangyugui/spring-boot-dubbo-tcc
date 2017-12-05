package com.eadydb.tcc.capital.mapper;

import com.eadydb.tcc.capital.entity.CapitalAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author eadydb
 * @date 2017-12-04 11:40
 * <p/>
 * @description
 */
@Mapper
public interface CapitalAccountMapper {

    CapitalAccount findByUserId(long userId);

    int update(CapitalAccount account);
}
