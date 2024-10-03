package com.example.entranceTest.controller;

import com.example.entranceTest.dto.CartDTO;
import com.example.entranceTest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam Long colorId,
            @RequestParam Long sizeId,
            @RequestParam Integer quantity) {
        CartDTO cartDTO = cartService.addToCart(userId, productId, colorId, sizeId, quantity);
        return ResponseEntity.ok(cartDTO);
    }
}
