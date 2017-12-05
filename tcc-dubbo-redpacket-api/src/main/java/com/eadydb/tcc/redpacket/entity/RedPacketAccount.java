package com.eadydb.tcc.redpacket.entity;

import com.eadydb.tcc.exception.InsufficientBalanceException;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 11:04
 * <p/>
 * @description
 */
public class RedPacketAccount implements Serializable {
    private long id;

    private long userId;

    private BigDecimal balanceAmount;

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
    }

    public void transferTo(BigDecimal amount) {
        this.balanceAmount = this.balanceAmount.add(amount);
    }

    public void cancelTransfer(BigDecimal amount) {
        transferTo(amount);
    }

}
