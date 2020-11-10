package com.chpark.msa.infra;

import com.chpark.msa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 2:30 AM
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
}
