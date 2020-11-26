package com.chpark.msa.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 2:29 AM
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
}
