package com.eadydb.tcc.capital.entity;


import com.eadydb.tcc.exception.InsufficientBalanceException;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 10:40
 * <p/>
 * @description
 */
public class CapitalAccount implements Serializable {

    private long id;

    private long userId;

    private BigDecimal balanceAmount;

    private BigDecimal transferAmount = BigDecimal.ZERO;

    public long getUserId() {
        return userId;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void transferFrom(BigDecimal amount) {

        this.balanceAmount = this.balanceAmount.subtract(amount);

        if (BigDecimal.ZERO.compareTo(this.balanceAmount) > 0) {
            throw new InsufficientBalanceException();
        }

        transferAmount = transferAmount.add(amount.negate());
    }

    public void transferTo(BigDecimal amount) {
        this.balanceAmount = this.balanceAmount.add(amount);
        transferAmount = transferAmount.add(amount);
    }

    public void cancelTransfer(BigDecimal amount) {
        transferTo(amount);
    }
}
