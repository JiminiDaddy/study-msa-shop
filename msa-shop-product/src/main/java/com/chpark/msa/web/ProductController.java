package com.chpark.msa.web;

import com.chpark.msa.domain.ResultCode;
import com.chpark.msa.exception.WrongProductIdException;
import com.chpark.msa.service.ProductService;
import com.chpark.msa.web.dto.ProductRegistRequestDto;
import com.chpark.msa.web.dto.ProductResponseDto;
import com.chpark.msa.web.dto.ResultCodeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param id 상품 Id
     */
    @GetMapping("/{id}")
    public ProductResponseDto find(@PathVariable Long id) {
        ProductResponseDto responseDto = productService.find(id);
        log.info("Find product. id:<{}>, name:<{}>, price:<{}>, stock:<{}>",
                responseDto.getId(), responseDto.getName(), responseDto.getPrice(), responseDto.getStockQuantity());
        return responseDto;
    }

    @GetMapping("/all")
    public List<ProductResponseDto> findAll() {
        return  productService.findAll();
    }

   /**
     * 상품 정보 변경 (가격, 재고 등)
     */
    @PutMapping
    public void addStock() {

    }


    public void removeStock() {
    }

    @ExceptionHandler(WrongProductIdException.class)
    public ResultCodeResponseDto exception1(WrongProductIdException e) {
        String message = e.getMessage();
        log.error(message);
        return new ResultCodeResponseDto(ResultCode.WRONG_PRODUCT_ID);
    }
}
