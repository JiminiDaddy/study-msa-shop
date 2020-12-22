package com.chpark.msa.web.dto;

import com.chpark.msa.domain.Product;
import com.chpark.msa.domain.ResultCode;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/14
 * Time : 5:01 PM
 */

@Getter
public class ProductResponseDto extends ResultCodeResponseDto{
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public ProductResponseDto(Product entity) {
        super(ResultCode.SUCCESS);
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
    }
}
