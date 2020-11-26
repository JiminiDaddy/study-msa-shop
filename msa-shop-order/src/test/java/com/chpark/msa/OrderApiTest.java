package com.chpark.msa;

import com.chpark.msa.api.MemberClient;
import com.chpark.msa.api.dto.MemberResponseDto;
import com.chpark.msa.web.dto.OrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/27
 * Time : 12:15 AM
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderApiTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MemberClient memberClient;

    private static final String SERVER_URL = "http://localhost:8005/api/v1/order";

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void 헬로_테스트() throws Exception {
        mvc.perform(get("http://localhost:8005/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().equals("hello");
    }

    @Test
    void 주문_생성하기() throws Exception {
        OrderRequestDto requestDto = createOrderRequest(1001L, 9001L);

        MemberResponseDto memberResponseDto = MemberResponseDto.builder().memberId(1001L).memberName("tester").build();

        when(memberClient.find(1001L)).thenReturn(memberResponseDto);

        mvc.perform(post(SERVER_URL)
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ordererId", Is.is(1001L)))
        ;
    }

    private OrderRequestDto createOrderRequest(Long ordererId, Long productId) {
        return OrderRequestDto.builder().ordererId(ordererId).productId(productId).build();
    }
}
