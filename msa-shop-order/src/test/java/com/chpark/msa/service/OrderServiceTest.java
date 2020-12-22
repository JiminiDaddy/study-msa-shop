package com.chpark.msa.service;

import com.chpark.msa.api.MemberClient;
import com.chpark.msa.api.ProductClient;
import com.chpark.msa.api.dto.MemberResponseDto;
import com.chpark.msa.api.dto.ProductResponseDto;
import com.chpark.msa.domain.OrderRepository;
import com.chpark.msa.web.dto.OrderCreateResponseDto;
import com.chpark.msa.web.dto.OrderRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.BDDMockito.given;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/28
 * Time : 3:51 PM
 */

@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MemberClient memberClient;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private OrderService orderService;

    @Test
    void 주문_생성하기() {
        // given
        Long ordererId = 1001L;
        String ordererName = "Test-User";
        Long productId = 5001L;
        int productCount = 1;
        int productPrice = 5000;
        given(memberClient.find(ordererId)).willReturn(MemberResponseDto.builder().memberId(ordererId).memberName(ordererName).build());
        given(productClient.find(productId)).willReturn((ProductResponseDto.builder().name("Book").count(productCount).price(productPrice).build()));
        OrderRequestDto requestDto = OrderRequestDto.builder().ordererId(ordererId).productId(productId).build();

        //when
        OrderCreateResponseDto responseDto = orderService.order(requestDto);

        //then
        Assertions.assertEquals(ordererId, responseDto.getOrdererId());
        Assertions.assertEquals(ordererName, responseDto.getOrdererName());
        Assertions.assertEquals(productCount * productPrice, responseDto.getTotalPriceAmount());
    }
}
