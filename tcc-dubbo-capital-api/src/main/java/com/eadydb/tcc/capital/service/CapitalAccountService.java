package com.eadydb.tcc.capital.service;

import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 10:56
 * <p/>
 * @description
 */
public interface CapitalAccountService {


    BigDecimal getCapitalAccountByUserId(long userId);

}
