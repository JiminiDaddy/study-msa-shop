package com.chpark.msa.domain;

import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/23
 * Time : 2:17 AM
 */

@Getter
public enum ResultCode {
    SUCCESS(100, "Success"),

    WRONG_PRODUCT_ID(110, "Wrong productId"),
    ;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;
}
