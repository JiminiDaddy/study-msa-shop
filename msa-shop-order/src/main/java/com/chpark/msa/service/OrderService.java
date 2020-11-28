package com.chpark.msa.service;

import com.chpark.msa.api.MemberClient;
import com.chpark.msa.api.ProductClient;
import com.chpark.msa.api.dto.MemberResponseDto;
import com.chpark.msa.api.dto.ProductResponseDto;
import com.chpark.msa.domain.*;
import com.chpark.msa.web.dto.OrderRequestDto;
import com.chpark.msa.web.dto.OrderCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/25
 * Time : 4:34 PM
 */

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final MemberClient memberClient;

    private final ProductClient productClient;

    @Transactional
    public String hello() {
        return "Hello";
    }

    @Transactional
    public OrderCreateResponseDto order(OrderRequestDto requestDto) {
        // 주문이 정상적으로 처리되려면 주문자의 유효성체크와 상품의 유효성체크 후 상품목록을 만드는 작업이 필요하다.
        // 이것은 Order Aggregate에서 처리하는것이 맞다고 본다.
        MemberResponseDto memberResponseDto = memberClient.find(requestDto.getOrdererId());
        // TODO Optional<MemberResponseDto>가 되면 아래와 같은 null처리가 없어질것으로 판단됨
        if (memberResponseDto == null) {
           throw new IllegalArgumentException("Wrong OrdererId: <" + requestDto.getOrdererId());
        }
        // TODO Product는 추후 구현
        Orderer orderer = Orderer.builder()
                .id(memberResponseDto.getMemberId())
                .name(memberResponseDto.getMemberName())
                .build();

        ProductResponseDto productResponseDto = productClient.find(requestDto.getProductId());
        OrderLine orderLine = OrderLine.builder()
                .productId(productResponseDto.getId())
                .count(productResponseDto.getCount())
                .price(productResponseDto.getPrice()).build();

        Order order = Order.createOrder(orderer, orderLine);

        orderRepository.save(order);

        return new OrderCreateResponseDto(order);
    }
}
