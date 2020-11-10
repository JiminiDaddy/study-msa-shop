package com.chpark.msa.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 1:44 AM
 */

@Getter
@Entity
@Table(name = "Members")
public class Member {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
}
