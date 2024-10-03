package com.example.entranceTest.mapper;

import com.example.entranceTest.dto.ProductDTO;
import com.example.entranceTest.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setDiscountedPrice(calculateDiscountPrice(product));
        dto.setRating(calculateAverageRating(product.getReviews()));
        dto.setRatingCount(product.getReviews().size());

        dto.setColors(product.getColors().stream()
                .map(Color::getColorName)
                .collect(Collectors.toList()));

        dto.setSizes(product.getSizes().stream()
                .map(Size::getSizeLabel)
                .collect(Collectors.toList()));

        dto.setImages(product.getImages().stream()
                .map(Image::getImageUrl)
                .collect(Collectors.toList()));
        dto.setStockCount(product.getStockCount());

        Date endDiscountDate = product.getDiscounts().stream()
                .filter(discount -> isDiscountActive(discount))
                .map(Discount::getEndDate)
                .max(Date::compareTo)
                .orElse(null);
        dto.setEndDiscountDate(endDiscountDate);
        return dto;
    }

    private static boolean isDiscountActive(Discount discount) {
        Date now = new Date();
        return now.after(discount.getStartDate()) && now.before(discount.getEndDate());
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
