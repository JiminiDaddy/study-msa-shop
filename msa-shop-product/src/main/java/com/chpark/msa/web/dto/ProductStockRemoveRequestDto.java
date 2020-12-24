package com.chpark.msa.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/24
 * Time : 8:52 AM
 */

@AllArgsConstructor
@Getter
public class ProductStockRemoveRequestDto {
    private Long id;
    private int  quantity;
}
