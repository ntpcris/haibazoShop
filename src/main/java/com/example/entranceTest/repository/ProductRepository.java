package com.example.entranceTest.repository;

import com.example.entranceTest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "JOIN p.colors c " +
            "JOIN p.sizes s " +
            "JOIN p.styles st " +
            "LEFT JOIN p.images img " +
            "LEFT JOIN p.reviews r " +
            "LEFT JOIN p.discounts d " +
            "WHERE (:categoryId IS NULL OR p.category.categoryId = :categoryId) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND (:colorId IS NULL OR c.colorId = :colorId) " +
            "AND (:sizeId IS NULL OR s.sizeId = :sizeId) " +
            "AND (:styleId IS NULL OR st.styleId = :styleId) " +
            "GROUP BY p.productId")
    List<Product> findFilteredProducts(@Param("categoryId") Long categoryId,
                                       @Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice,
                                       @Param("colorId") Long colorId,
                                       @Param("sizeId") Long sizeId,
                                       @Param("styleId") Long styleId);

}
