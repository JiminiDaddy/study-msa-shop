package com.chpark.msa.web.dto;

import com.chpark.msa.domain.Member;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/13
 * Time : 12:20 AM
 */

@Getter
public class MemberResponseDto {
    private Long memberId;

    private String memberName;

    public MemberResponseDto(Member member) {
        this.memberId = member.getId();
        this.memberName = member.getName();
    }
}
