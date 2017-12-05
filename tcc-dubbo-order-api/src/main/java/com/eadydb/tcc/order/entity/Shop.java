package com.eadydb.tcc.order.entity;

import java.io.Serializable;

/**
 * @author eadydb
 * @date 2017-12-04 10:32
 * <p/>
 * @description
 */
public class Shop implements Serializable{

    private long id;

    private long ownerUserId;

    public long getOwnerUserId() {
        return ownerUserId;
    }

    public long getId() {
        return id;
    }
}
