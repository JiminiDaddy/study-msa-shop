package com.chpark.msa.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 2:13 AM
 */

@Getter
@Entity
@Table(name = "Products")
public class Product {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private int stock;

    private int price;
}
