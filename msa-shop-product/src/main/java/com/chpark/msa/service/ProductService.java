package com.chpark.msa.service;

import com.chpark.msa.domain.Product;
import com.chpark.msa.domain.ProductRepository;
import com.chpark.msa.domain.ResultCode;
import com.chpark.msa.exception.WrongProductIdException;
import com.chpark.msa.web.dto.ProductRegistRequestDto;
import com.chpark.msa.web.dto.ProductResponseDto;
import com.chpark.msa.web.dto.ProductStockAddRequestDto;
import com.chpark.msa.web.dto.ProductStockRemoveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public ProductResponseDto find(Long productId) {
        return new ProductResponseDto(findById(productId));
    }

    @Transactional
    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDto addStock(ProductStockAddRequestDto requestDto)  {
        Product product = findById(requestDto.getId());
        product.addStock(requestDto.getQuantity());
        return new ProductResponseDto(productRepository.save(product));
    }

    @Transactional
    public ProductResponseDto removeStock(ProductStockRemoveRequestDto requestDto) {
       Product product = findById(requestDto.getId());
       product.removeStock(requestDto.getQuantity());
       return new ProductResponseDto(productRepository.save(product));
    }

    private Product findById(Long productId) {
         return productRepository.findById(productId).orElseThrow(
                () -> new WrongProductIdException("Wrong productId: <" + productId + ">"));
    }
}
