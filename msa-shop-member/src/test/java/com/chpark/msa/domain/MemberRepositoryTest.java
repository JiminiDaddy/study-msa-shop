package com.chpark.msa.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/30
 * Time : 3:37 PM
 */

@Transactional
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        String memberName = "Chpark";
        Member member = createMember(memberName);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Assertions.assertEquals(memberName, savedMember.getName());
    }

    @Test
    void 회원조회() {
        // given
        String name1 = "Chpark";
        String name2 = "Jimin";
        Member member = createMember(name1);
        Long memberId1 = memberRepository.save(member).getId();
        member = createMember(name2);
        Long memberId2 = memberRepository.save(member).getId();

        // when
        long memberCount = memberRepository.count();
        Member findMember1 = memberRepository.findById(memberId1).orElseThrow(() -> new IllegalArgumentException("Wrong Id: <" + memberId1 + ">"));
        Member findMember2 = memberRepository.findById(memberId2).orElseThrow(() -> new IllegalArgumentException("Wrong Id: <" + memberId2 + ">"));

        // then
        Assertions.assertEquals(2, memberCount);
        Assertions.assertEquals(name1, findMember1.getName());
        Assertions.assertEquals(name2, findMember2.getName());
    }

    private Member createMember(String memberName) {
        return Member.builder().name(memberName).build();
    }
}
