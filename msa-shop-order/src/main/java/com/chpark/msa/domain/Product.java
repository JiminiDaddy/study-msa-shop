package com.chpark.msa.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/26
 * Time : 12:44 AM
 */

@Builder
@Getter
@Embeddable
public class Product {
    private Long productId;

    private int count;

    private int price;
}
