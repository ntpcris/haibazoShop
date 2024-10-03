package com.example.entranceTest.service;

import com.example.entranceTest.dto.CartDTO;
import com.example.entranceTest.entity.Cart;
import com.example.entranceTest.entity.CartItem;
import com.example.entranceTest.entity.ProductVariant;
import com.example.entranceTest.entity.User;
import com.example.entranceTest.mapper.CartItemMapper;
import com.example.entranceTest.repository.CartRepository;
import com.example.entranceTest.repository.ProductVariantRepository;
import com.example.entranceTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private UserRepository userRepository;

    public CartDTO addToCart(Long userId, Long productId, Long colorId, Long sizeId, Integer quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return newCart;
                });

        ProductVariant productVariant = productVariantRepository
                .findByProductProductIdAndColorColorIdAndSizeSizeId(productId, colorId, sizeId)
                .orElseThrow(() -> new RuntimeException("Product variant not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProductVariant(productVariant);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);
        Cart savedCart = cartRepository.save(cart);

        return toCartDTO(savedCart);
    }

    private CartDTO toCartDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setUserId(cart.getUser().getUserId());
        dto.setItems(cart.getItems().stream()
                .map(cartItemMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}
