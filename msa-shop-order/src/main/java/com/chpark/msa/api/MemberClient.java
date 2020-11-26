package com.chpark.msa.api;

import com.chpark.msa.api.dto.MemberResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/25
 * Time : 4:41 PM
 */

@FeignClient (name = "msa-member-api", url = "localhost:8006")
public interface MemberClient {
    // TODO Optional로 반환하는것이 좋지 않을까?
    @GetMapping("/api/v1/member/{id}")
    MemberResponseDto find(@PathVariable Long id);
}
