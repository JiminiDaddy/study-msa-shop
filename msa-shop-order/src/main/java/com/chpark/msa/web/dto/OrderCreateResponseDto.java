package com.chpark.msa.web.dto;

import com.chpark.msa.domain.Order;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/25
 * Time : 4:29 PM
 */

@Getter
public class OrderCreateResponseDto {
    private Long orderId;

    private Long ordererId;

    private String ordererName;

    private int totalPriceAmount;

    public OrderCreateResponseDto(Order order) {
        this.orderId = order.getId();
        this.ordererId = order.getOrderer().getId();
        this.ordererName = order.getOrderer().getName();
        this.totalPriceAmount = order.totalPrice();
    }
}
