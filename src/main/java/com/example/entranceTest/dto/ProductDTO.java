package com.example.entranceTest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class ProductDTO {

    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private Integer rating;
    private Integer ratingCount;
    private List<String> colors;
    private List<String> sizes;
    private List<String> images;
    private Integer stockCount;
    private Date endDiscountDate;

}
