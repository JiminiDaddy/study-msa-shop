package com.chpark.msa.web.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/25
 * Time : 4:23 PM
 */

@Builder
@Getter
public class OrderRequestDto {
    private Long ordererId;
    // TODO 주문1건당 상품은 N개가 포함될 수 있으므로, 추후 Collection으로 변경한다.
    private Long productId;
}
