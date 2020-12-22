package com.chpark.msa.web.dto;

import com.chpark.msa.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/14
 * Time : 4:57 PM
 */

@NoArgsConstructor
@Getter
public class ProductRegistRequestDto {
    private String name;
    private int price;
    private int quantity;

    @Builder
    ProductRegistRequestDto(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .stockQuantity(quantity)
                .build();
    }
}
