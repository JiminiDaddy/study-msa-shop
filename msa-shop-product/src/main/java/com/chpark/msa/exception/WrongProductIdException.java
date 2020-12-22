package com.chpark.msa.exception;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/23
 * Time : 2:45 AM
 */

public class WrongProductIdException extends RuntimeException{
    public WrongProductIdException(String message) {
        super(message);
    }
}
