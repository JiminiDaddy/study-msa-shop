package com.chpark.msa.web.dto;

import com.chpark.msa.domain.ResultCode;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/23
 * Time : 2:15 AM
 */

@Getter
public class ResultCodeResponseDto {
    private int code;
    private String message;

    public ResultCodeResponseDto(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
