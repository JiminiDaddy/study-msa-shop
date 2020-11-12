package com.chpark.msa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.StringTokenizer;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 1:44 AM
 */

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Members")
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public Member(String name) {
        this.name = name;
    }
}
