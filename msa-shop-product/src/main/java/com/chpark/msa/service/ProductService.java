package com.chpark.msa.service;

import com.chpark.msa.domain.Product;
import com.chpark.msa.domain.ProductRepository;
import com.chpark.msa.web.dto.ProductRegistRequestDto;
import com.chpark.msa.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/12/14
 * Time : 5:12 PM
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto regist(ProductRegistRequestDto requestDto) {
        // TODO 상품의 고유성을 보장해야함
        // TODO 카테고리 등록하여 상품 구분필요
        Product product = productRepository.save(requestDto.toEntity());
        return new ProductResponseDto(product);
    }
}
