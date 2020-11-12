package com.chpark.msa.service;

import com.chpark.msa.domain.Member;
import com.chpark.msa.infra.MemberRepository;
import com.chpark.msa.web.dto.MemberJoinRequestDto;
import com.chpark.msa.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/13
 * Time : 12:21 AM
 */

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto joinMember(MemberJoinRequestDto dto) {
        Member member = memberRepository.save(dto.toEntity());
        return new MemberResponseDto(member);
    }

    @Transactional
    public MemberResponseDto findMember(Long memberId) {
        // TODO Spec을 구현해서 ID외에 Name 포함 검색조건도 구현해야함
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Wrong memberId: <" + memberId + ">"));
        return new MemberResponseDto(member);
    }
}
