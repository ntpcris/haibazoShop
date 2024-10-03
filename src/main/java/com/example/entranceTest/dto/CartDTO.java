package com.example.entranceTest.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long userId;
    private List<CartItemDTO> items;
}
