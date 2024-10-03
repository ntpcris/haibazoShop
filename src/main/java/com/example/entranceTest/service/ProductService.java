package com.example.entranceTest.service;

import com.example.entranceTest.dto.ProductDTO;
import com.example.entranceTest.dto.ProductFilterDTO;
import com.example.entranceTest.entity.Product;
import com.example.entranceTest.mapper.ProductFilterMapper;
import com.example.entranceTest.mapper.ProductMapper;
import com.example.entranceTest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductFilterDTO> getFilteredProducts(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Long colorId, Long sizeId, Long styleId) {
        List<Product> products = productRepository.findFilteredProducts(categoryId, minPrice, maxPrice, colorId, sizeId, styleId);
        return products.stream().map(ProductFilterMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            return ProductMapper.toDTO(productOpt.get());
        } else {
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }
}
