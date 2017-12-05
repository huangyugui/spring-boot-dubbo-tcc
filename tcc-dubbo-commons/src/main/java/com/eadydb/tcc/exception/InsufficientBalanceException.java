package com.eadydb.tcc.exception;

/**
 * @author eadydb
 * @date 2017-12-04 10:43
 * <p/>
 * @description
 */
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {

    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}