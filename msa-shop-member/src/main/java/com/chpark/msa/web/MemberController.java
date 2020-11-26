package com.chpark.msa.web;

import com.chpark.msa.service.MemberService;
import com.chpark.msa.web.dto.MemberJoinRequestDto;
import com.chpark.msa.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/13
 * Time : 12:17 AM
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public MemberResponseDto joinMember(@RequestBody MemberJoinRequestDto dto) {
        log.info("Requested, joinMember. memberName:<{}>", dto.getName());
        return memberService.joinMember(dto);
    }

    @GetMapping("/api/v1/member/{id}")
    public MemberResponseDto findMember(@PathVariable(name = "id") Long id) {
        log.info("Requested, findMember. memberId:<{}>", id);
        return memberService.findMember(id);
    }
}
