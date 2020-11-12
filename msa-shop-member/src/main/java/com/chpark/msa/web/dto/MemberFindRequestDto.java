package com.chpark.msa.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/13
 * Time : 12:25 AM
 */

@NoArgsConstructor
@Getter
public class MemberFindRequestDto {
    private Long memberId;

    private String memberName;

    @Builder
    public MemberFindRequestDto(Long memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }
}
