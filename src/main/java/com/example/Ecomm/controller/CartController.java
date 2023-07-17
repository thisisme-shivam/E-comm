package com.example.Ecomm.controller;

import com.example.Ecomm.model.CartItem;
import com.example.Ecomm.model.Product;
import com.example.Ecomm.model.Cart;
import com.example.Ecomm.model.User;
import com.example.Ecomm.service.CartService;
import com.example.Ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addCart/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Integer userId) {
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Integer userId, @PathVariable Integer productId) {
        Cart cart = cartService.addProductToCart(userId, productId);
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Integer userId, @PathVariable Integer productId) {
        Cart cart = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer userId) {
        Cart cart = cartService.findCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

}
