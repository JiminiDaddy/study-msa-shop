package com.chpark.msa.web;

import com.chpark.msa.service.OrderService;
import com.chpark.msa.web.dto.OrderCreateResponseDto;
import com.chpark.msa.web.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/25
 * Time : 4:19 PM
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/order")
    public OrderCreateResponseDto order(@RequestBody OrderRequestDto requestDto) {
        log.info("requested ordererId: <{}>" , requestDto.getOrdererId());
        return orderService.order(requestDto);
    }

    @GetMapping("/hello")
    public String hello() {
        String hello = orderService.hello();
        log.info("hello: " + hello);
        return hello;
    }
}
