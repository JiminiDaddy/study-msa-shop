package com.chpark.msa.exception;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/21
 * Time : 12:56 AM
 */

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String message) {
       super(message);
    }
}
