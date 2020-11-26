package com.chpark.msa.web;

import com.chpark.msa.domain.Member;
import com.chpark.msa.domain.MemberRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        joinMember(requestDto);

        List<Member> savedMembers = memberRepository.findAll();
        Assert.assertEquals(1, savedMembers.size());
        Assert.assertEquals(name, savedMembers.get(0).getName());
    }

    @Test
    public void 회원_조회_테스트() throws Exception {
        String name = "CheonHeePark";
        MemberJoinRequestDto requestDto = MemberJoinRequestDto.builder().name(name).build();
        joinMember(requestDto);
        List<Member> savedMembers = memberRepository.findAll();
        Assert.assertEquals(1, savedMembers.size());
        Member savedMember = savedMembers.get(0);
        String result = findMember(savedMember.getId());
        System.out.println("findResult: " + result);
        savedMember = memberRepository.findById(savedMember.getId()).orElseThrow(() -> new IllegalArgumentException("ERROR"));
        Assert.assertEquals(name, savedMember.getName());
    }

    private void joinMember(MemberJoinRequestDto requestDto)throws Exception {
         mvc.perform(post(JOIN_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                ;
    }

    private String findMember(Long id) throws Exception {
        String result =
        mvc.perform(get(JOIN_URL + "/" + id))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        return result;
    }
}
