package com.chpark.msa.api.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/29
 * Time : 3:51 AM
 */

@Builder
@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int count;
    private int price;
}
