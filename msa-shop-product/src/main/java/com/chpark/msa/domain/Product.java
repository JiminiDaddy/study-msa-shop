package com.chpark.msa.domain;

import com.chpark.msa.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 2:13 AM
 */

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Products")
public class Product {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private int stockQuantity;

    private int price;

    @Builder
    Product(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    public void removeStock(int stockQuantity) {
        if (stockQuantity > this.stockQuantity) {
            throw new NotEnoughStockException("Need more stock");
        }
        this.stockQuantity -= stockQuantity;
    }
}
