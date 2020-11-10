package com.chpark.msa.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 1:33 AM
 */

@Getter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    // TODO MSA로 구성하므로 Order Aggregate는 MemberRepository에 직접 접근하지 않고, MQ를 이용해서 가져오게 구현할 예정
    private Long ordererId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderLine> orderLines = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    public Long order(Long ordererId, OrderLine... orderLines) {
        setOrderer(ordererId);
        this.orderLines.addAll(Arrays.asList(orderLines));
        changeOrderStatus(OrderStatus.ORDER);
        return 0L;
    }

    public void cancel() {
        for (OrderLine orderLine : orderLines) {
            orderLine.cancel();
        }
        changeOrderStatus(OrderStatus.CANCEL);
    }

    public int totalPrice() {
        int price = 0;
        for (OrderLine orderLine : orderLines) {
            price += orderLine.getOrderPrice();
        }
        return price;
    }

    private void setOrderer(Long ordererId) {
        this.ordererId = ordererId;
    }

    private void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
