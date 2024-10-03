package com.example.entranceTest.mapper;

import com.example.entranceTest.dto.ProductFilterDTO;
import com.example.entranceTest.entity.Discount;
import com.example.entranceTest.entity.Product;
import com.example.entranceTest.entity.Review;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductFilterMapper {

    public static ProductFilterDTO toDTO(Product product){
        ProductFilterDTO dto = new ProductFilterDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setDiscountPrice(calculateDiscountPrice(product));

        if(!product.getImages().isEmpty()){
            dto.setImageUrl(product.getImages().get(0).getImageUrl());
        }

        dto.setRating(calculateAverageRating(product.getReviews()));

        return dto;
    }

    private static Integer calculateAverageRating(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0;
        }
        int totalRating = reviews.stream().mapToInt(Review::getRating).sum();
        return totalRating / reviews.size();
    }

    private static BigDecimal calculateDiscountPrice(Product product) {
        BigDecimal originalPrice = product.getPrice();
        BigDecimal finalPrice = originalPrice;

        Date now = new Date();

        for(Discount discount : product.getDiscounts()){
            if(now.after(discount.getStartDate()) && now.before(discount.getEndDate())){
                BigDecimal discountAmount = originalPrice.multiply(discount
                        .getDiscountPercentage()
                        .divide(new BigDecimal(100)));

                BigDecimal discountPrice = originalPrice.subtract(discountAmount);

                if (discountPrice.compareTo(finalPrice) < 0) {
                    finalPrice = discountPrice;
                }
            }
        }
        return finalPrice;
    }
}
