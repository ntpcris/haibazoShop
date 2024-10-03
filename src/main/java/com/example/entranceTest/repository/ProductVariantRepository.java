package com.example.entranceTest.repository;

import com.example.entranceTest.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Optional<ProductVariant> findByProductProductIdAndColorColorIdAndSizeSizeId(Long productId, Long colorId, Long sizeId);
}
