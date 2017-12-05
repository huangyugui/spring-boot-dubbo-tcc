package com.eadydb.tcc.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author eadydb
 * @date 2017-12-04 10:19
 * <p/>
 * @description
 */
public class OrderLine implements Serializable {

    private long id;

    private long productId;

    private int quantity;

    private BigDecimal unitPrice;

    public OrderLine() {
    }

    public OrderLine(long productId, int quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalAmount() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public long getId() {
        return id;
    }
}
