package com.chpark.msa.web.dto;

import com.chpark.msa.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/13
 * Time : 12:19 AM
 */

@NoArgsConstructor
@Getter
public class MemberJoinRequestDto {
   private String name;

   public Member toEntity() {
      return Member.builder().name(name).build();
   }

   @Builder
   public MemberJoinRequestDto(String name) {
      this.name = name;
   }
}
