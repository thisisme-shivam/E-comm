package com.example.Ecomm.repository;

import com.example.Ecomm.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByUserId(Integer userId);
}

