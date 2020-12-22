package com.chpark.msa.domain;

import com.chpark.msa.exception.NotEnoughStockException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * User : chpark
 * Created by Choen-hee Park
 * Date : 2020/12/21
 * Time : 1:02 AM
 */

@Transactional
@DataJpaTest
class ProductDomainTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 상품_재고_기능_테스트() {
        Product springDataJPABook = Product.builder().name("spring-data-jpa-book").price(20000).stockQuantity(100).build();
        springDataJPABook = productRepository.save(springDataJPABook);
        Product macbookPro = Product.builder().name("macbook-pro").price(3000000).stockQuantity(5).build();
        macbookPro = productRepository.save(macbookPro);

        Product findSpringDataJPABook = productRepository.findById(springDataJPABook.getId()).orElseThrow(() -> new IllegalArgumentException("Id Error"));
        Assertions.assertEquals(findSpringDataJPABook.getName(), springDataJPABook.getName());
        Product findMacbookPro = productRepository.findById(macbookPro.getId()).orElseThrow(() -> new IllegalArgumentException("Id Error"));
        Assertions.assertEquals(findMacbookPro.getName(), macbookPro.getName());

        findSpringDataJPABook.addStock(50);
        Assertions.assertEquals(150, findSpringDataJPABook.getStockQuantity());
        findSpringDataJPABook.removeStock(20);
        Assertions.assertEquals(130, findSpringDataJPABook.getStockQuantity());

        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            System.out.println("NotEnoughStockException!");
            findMacbookPro.removeStock(20);
        });
    }
}
