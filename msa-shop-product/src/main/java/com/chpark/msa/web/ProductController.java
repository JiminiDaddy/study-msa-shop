package com.chpark.msa.web;

import com.chpark.msa.service.ProductService;
import com.chpark.msa.web.dto.ProductRegistRequestDto;
import com.chpark.msa.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/14
 * Time : 4:54 PM
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    /**
     * 상품 등록
     */
    @PostMapping
    public ProductResponseDto regist(@RequestBody ProductRegistRequestDto requestDto) {
        ProductResponseDto responseDto = productService.regist(requestDto);
        log.info("Registered new-product. id:<{}>", responseDto.getId());
        return responseDto;
    }

    /**
     * 상품 정보 가져오기
     * @param id
     */
    @GetMapping("/{id}")
    public void find(@PathVariable Long id) {
        log.info("Find, id: <{}>", id);
    }

   /**
     * 상품 정보 변경 (가격, 재고 등)
     */
    @PutMapping
    public void addStock() {

    }


    public void removeStock() {
    }
}
