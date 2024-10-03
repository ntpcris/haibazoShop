package com.example.entranceTest.controller;

import com.example.entranceTest.dto.ProductDTO;
import com.example.entranceTest.dto.ProductFilterDTO;
import com.example.entranceTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/filter")
    public ResponseEntity<List<ProductFilterDTO>> getAllProducts(
            @RequestParam(value = "category", required = false) Long categoryId,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "color", required = false) Long colorId,
            @RequestParam(value = "size", required = false) Long sizeId,
            @RequestParam(value = "style", required = false) Long styleId
    ) {

        List<ProductFilterDTO> products = productService.getFilteredProducts(categoryId, minPrice, maxPrice, colorId, sizeId, styleId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
}
