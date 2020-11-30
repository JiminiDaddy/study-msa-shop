package com.chpark.msa.service;

import com.chpark.msa.domain.Member;
import com.chpark.msa.domain.MemberRepository;
import com.chpark.msa.web.dto.MemberJoinRequestDto;
import com.chpark.msa.web.dto.MemberResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/30
 * Time : 3:59 PM
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
class MemberServiceTest {
    @SpyBean
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        String memberName = "Chpark";
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto(memberName);

        MemberResponseDto responseDto = memberService.joinMember(requestDto);
        Assertions.assertEquals("Chpark", responseDto.getMemberName());
        Assertions.assertNotNull(responseDto.getMemberId());
    }

    @Test
    void 회원조회() {
        String memberName = "Chpark";
        MemberJoinRequestDto requestDto = new MemberJoinRequestDto(memberName);
        Member savedMember = memberRepository.save(requestDto.toEntity());

        Assertions.assertEquals(memberName, memberService.findMember(savedMember.getId()).getMemberName());
    }
}
