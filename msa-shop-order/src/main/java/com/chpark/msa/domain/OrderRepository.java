package com.chpark.msa.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 2:15 AM
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
}
