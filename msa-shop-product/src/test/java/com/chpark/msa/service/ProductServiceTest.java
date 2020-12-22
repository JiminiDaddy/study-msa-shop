package com.chpark.msa.service;

import com.chpark.msa.domain.Product;
import com.chpark.msa.web.dto.ProductRegistRequestDto;
import com.chpark.msa.web.dto.ProductResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/21
 * Time : 1:21 AM
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void 상품_등록() {
        ProductRegistRequestDto requestDto = ProductRegistRequestDto.builder().name("macbook-pro").price(3000000).quantity(5).build();
        Product macbookPro = requestDto.toEntity();

        ProductResponseDto responseDto = productService.regist(requestDto);

        Assertions.assertNotNull(responseDto.getId());
        Assertions.assertEquals(responseDto.getName(), macbookPro.getName());
        Assertions.assertEquals(responseDto.getStockQuantity(), macbookPro.getStockQuantity());
    }
}
