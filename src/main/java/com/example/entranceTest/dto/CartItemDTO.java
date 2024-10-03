package com.example.entranceTest.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private Long productVariantId;
    private Integer quantity;
}
