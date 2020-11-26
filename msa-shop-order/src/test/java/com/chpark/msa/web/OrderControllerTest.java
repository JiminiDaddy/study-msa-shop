package com.chpark.msa.web;

import com.chpark.msa.domain.Order;
import com.chpark.msa.domain.OrderLine;
import com.chpark.msa.domain.Orderer;
import com.chpark.msa.service.OrderService;
import com.chpark.msa.web.dto.OrderCreateResponseDto;
import com.chpark.msa.web.dto.OrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/26
 * Time : 12:54 AM
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mvc;

    private static final String SERVER_URL = "http://localhost:8005/api/v1/order";

    @Test
    void 헬로_테스트() throws Exception {
        when(orderService.hello()).thenReturn("hellossss");

        final ResultActions actions = mvc.perform(get("http://localhost:8005/hello")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        actions.andExpect(content().string(containsString("Hello")));
    }

    @Test
    void 주문_생성_테스트() throws Exception {
        Long orderId = 1001L;
        Long productId = 5001L;
        int productCount = 10;
        int productPrice = 10000;
        OrderRequestDto requestDto = createOrderRequestDto(orderId, productId);

        Orderer orderer = Orderer.builder().id(orderId).name("Test-Orderer").build();
        OrderLine orderLine = OrderLine.builder().productId(productId).count(productCount).price(productPrice).build();
        Order order = Order.createOrder(orderer, orderLine);
        OrderCreateResponseDto responseDto = new OrderCreateResponseDto(order);

        given(orderService.order(requestDto)).willReturn(responseDto);

        final ResultActions actions = mvc.perform(post(SERVER_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        actions.andDo(print());
    }

    private OrderRequestDto createOrderRequestDto(Long ordererId, Long productId) {
        return OrderRequestDto.builder().ordererId(ordererId).productId(productId).build();
    }
}
