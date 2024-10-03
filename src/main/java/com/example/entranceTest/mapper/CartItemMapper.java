package com.example.entranceTest.mapper;

import com.example.entranceTest.dto.CartItemDTO;
import com.example.entranceTest.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDTO toDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setProductVariantId(cartItem.getProductVariant().getProductVariantId());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }
    public CartItem toEntity(CartItemDTO dto) {
        CartItem cartItem = new CartItem();
        cartItem.setId(dto.getId());

        // cartItem.setProductVariant(productVariant);
        cartItem.setQuantity(dto.getQuantity());
        return cartItem;
    }
}
