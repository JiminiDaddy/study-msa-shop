package com.chpark.msa.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/28
 * Time : 3:43 PM
 */

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 주문_등록하기() {
        Long ordererId = 1001L;
        Long productId = 5001L;
        Orderer orderer = createOrderer(ordererId, "Test-User");
        OrderLine orderLine = createOrderLine(productId, 10, 1000);
        Order order = Order.createOrder(orderer, orderLine);
        Order savedOrder = orderRepository.save(order);

        Assertions.assertEquals(1, orderRepository.count());
        Assertions.assertEquals("Test-User", savedOrder.getOrderer().getName());

        Long otherOrdererId = 1002L;
        orderer = createOrderer(otherOrdererId, "Good-User");
        orderLine = createOrderLine(productId, 5, 100);
        order = Order.createOrder(orderer, orderLine);
        savedOrder = orderRepository.save(order);

        Assertions.assertEquals(2, orderRepository.count());
        Assertions.assertEquals("Good-User", savedOrder.getOrderer().getName());
    }

    private Orderer createOrderer(Long id, String name) {
        return Orderer.builder().id(id).name(name).build();
    }

    private OrderLine createOrderLine(Long productId, int count, int price) {
       return OrderLine.builder().productId(productId) .count(count).price(price).build();
    }
}
