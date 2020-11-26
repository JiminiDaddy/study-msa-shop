package com.chpark.msa.api.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/13
 * Time : 12:20 AM
 */
@Builder
@Getter
public class MemberResponseDto {
    private Long memberId;

    private String memberName;
}
