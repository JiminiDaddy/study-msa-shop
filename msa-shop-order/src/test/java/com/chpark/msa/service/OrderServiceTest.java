package com.chpark.msa.service;

import com.chpark.msa.api.MemberClient;
import com.chpark.msa.api.ProductClient;
import com.chpark.msa.api.dto.MemberResponseDto;
import com.chpark.msa.api.dto.ProductResponseDto;
import com.chpark.msa.domain.Order;
import com.chpark.msa.domain.OrderLine;
import com.chpark.msa.domain.OrderRepository;
import com.chpark.msa.domain.Orderer;
import com.chpark.msa.web.dto.OrderCreateResponseDto;
import com.chpark.msa.web.dto.OrderRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

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
        Long ordererId = 1001L;
        String ordererName = "Test-User";
        Long productId = 5001L;
        given(memberClient.find(ordererId)).willReturn(MemberResponseDto.builder().memberId(ordererId).memberName(ordererName).build());

        given(productClient.find(productId)).willReturn((ProductResponseDto.builder().name("Book").count(30).price(100).build()));

        Orderer orderer = createOrderer(ordererId, ordererName);
        OrderLine orderLine = createOrderLine(productId, 10, 1000);
        Order order = Optional.of(Order.createOrder(orderer, orderLine)).orElseThrow(() -> new IllegalArgumentException("Failed, Create Order."));
        given((orderRepository.findById(ordererId))).willReturn(Optional.of(order));

        OrderRequestDto requestDto = OrderRequestDto.builder().ordererId(ordererId).productId(productId).build();

        OrderCreateResponseDto responseDto = orderService.order(requestDto);
        Assertions.assertEquals(ordererId, responseDto.getOrdererId());
        Assertions.assertEquals(ordererName, responseDto.getOrdererName());
        Assertions.assertEquals(30 * 100, responseDto.getTotalPriceAmount());
    }

    private Orderer createOrderer(Long id, String name) {
        return Orderer.builder().id(id).name(name).build();
    }

    private OrderLine createOrderLine(Long productId, int count, int price) {
        return OrderLine.builder().productId(productId) .count(count).price(price).build();
    }
}
