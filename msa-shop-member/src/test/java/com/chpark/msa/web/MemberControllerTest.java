package com.chpark.msa.web;

import com.chpark.msa.domain.Member;
import com.chpark.msa.infra.MemberRepository;
import com.chpark.msa.web.dto.MemberJoinRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/13
 * Time : 12:51 AM
 */

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class MemberControllerTest {
    private static final String JOIN_URL = "http://localhost:8006/api/v1/member";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void setup() {
       mvc = MockMvcBuilders
               .webAppContextSetup(context)
               .build();
    }

    @Test
    public void 회원_가입_테스트() throws Exception {
        String name = "CheonHeePark";
        MemberJoinRequestDto requestDto = MemberJoinRequestDto.builder().name(name).build();

        mvc.perform(post(JOIN_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                ;

        List<Member> savedMembers = memberRepository.findAll();
        Assert.assertEquals(1, savedMembers.size());
        Assert.assertEquals(name, savedMembers.get(0).getName());
    }
}
