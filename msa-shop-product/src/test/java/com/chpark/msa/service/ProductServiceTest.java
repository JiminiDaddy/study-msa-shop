package com.chpark.msa.service;

import com.chpark.msa.domain.Product;
import com.chpark.msa.exception.NotEnoughStockException;
import com.chpark.msa.web.dto.ProductRegistRequestDto;
import com.chpark.msa.web.dto.ProductResponseDto;
import com.chpark.msa.web.dto.ProductStockAddRequestDto;
import com.chpark.msa.web.dto.ProductStockRemoveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/21
 * Time : 1:21 AM
 */

@Slf4j
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void 상품_등록() {
        ProductRegistRequestDto requestDto = createRegistRequestDto("macbook-pro", 3000000, 5);
        Product macbookPro = requestDto.toEntity();

        ProductResponseDto responseDto = productService.regist(requestDto);

        Assertions.assertNotNull(responseDto.getId());
        Assertions.assertEquals(responseDto.getName(), macbookPro.getName());
        Assertions.assertEquals(responseDto.getStockQuantity(), macbookPro.getStockQuantity());
    }

    @Test
    void 상품_조회() {
        ProductRegistRequestDto registRequestDto= createRegistRequestDto("macbook-pro", 3000000, 5);
        ProductResponseDto registResponseDto = productService.regist(registRequestDto);

        ProductResponseDto findResponseDto = productService.find(registResponseDto.getId());
        Assertions.assertNotNull(findResponseDto.getId());
        Assertions.assertEquals("macbook-pro", findResponseDto.getName());

        registRequestDto= createRegistRequestDto("macbook-air", 1000000, 25);
        productService.regist(registRequestDto);

        registRequestDto= createRegistRequestDto("playStation", 500000, 10);
        productService.regist(registRequestDto);

        List<ProductResponseDto> findAllResponse = productService.findAll();
        Assertions.assertEquals(3, findAllResponse.size());
    }

    @Test
    void 상품_재고_추가_제거() {
        ProductRegistRequestDto requestDto = createRegistRequestDto("bread", 10000, 10);

        ProductResponseDto responseDto = productService.regist(requestDto);
        Long productId = responseDto.getId();
        Assertions.assertNotNull(productId);

        // 재고 추가 (10 + 30 = 40)
        ProductStockAddRequestDto stockAddRequestDto = createStockAddRequestDto(productId, 30);
        productService.addStock(stockAddRequestDto);
        Assertions.assertEquals(40, productService.find(productId).getStockQuantity());

        // 재고 감축 (40 - 20 = 20)
        ProductStockRemoveRequestDto stockRemoveRequestDto = createStockRemoveRequestDto(productId, 20);
        productService.removeStock(stockRemoveRequestDto);
        Assertions.assertEquals(20, productService.find(productId).getStockQuantity());

        // 재고 0밑으로 될 경우 예외 (20 - 21 = -1)
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            log.info("NotEnougpq StockException!");
            ProductStockRemoveRequestDto stockRemoveRequestDto2 = createStockRemoveRequestDto(productId, 21);
            productService.removeStock(stockRemoveRequestDto2);
        });
    }

    private ProductRegistRequestDto createRegistRequestDto(String name, int price, int stockQuantity) {
        return new ProductRegistRequestDto(name, price, stockQuantity);
    }

    private ProductStockAddRequestDto createStockAddRequestDto(long productId, int stockQuantity) {
        return new ProductStockAddRequestDto(productId, stockQuantity);
    }

    private ProductStockRemoveRequestDto createStockRemoveRequestDto(long productId, int stockQuantity) {
        return new ProductStockRemoveRequestDto(productId, stockQuantity);
    }
}
