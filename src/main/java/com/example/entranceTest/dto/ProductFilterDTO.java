package com.example.entranceTest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilterDTO {

    private Long productId;
    private String productName;
    private BigDecimal Price;
    private BigDecimal discountPrice;
    private String imageUrl;
    private Integer rating;

}
