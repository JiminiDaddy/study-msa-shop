package com.chpark.msa.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 2:02 AM
 */

@Getter
@Entity
@Table(name = "OrderLines")
public class OrderLine {
   @Id @GeneratedValue
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Order order;

   // 상품 아이디
   private Long productId;

   // 주문한 상품의 개수
   private int count;

   // 주문목록의 가격 (상품 개수 x 상품 가격)
   private int orderPrice;

   @Builder
   public OrderLine(Long productId, int price, int count) {
     this.productId = productId;
     this.count = count;
     this.orderPrice = price * count;
   }

   void cancel() {
      count = 0;
      orderPrice = 0;
   }
}
