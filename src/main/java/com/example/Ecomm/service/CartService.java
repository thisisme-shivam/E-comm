package com.example.Ecomm.service;

import com.example.Ecomm.model.CartItem;
import com.example.Ecomm.model.Product;
import com.example.Ecomm.model.Cart;
import com.example.Ecomm.model.User;
import com.example.Ecomm.repository.CartItemRepository;
import com.example.Ecomm.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private  CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    public Cart createCart(Integer userId) {
        User user = userService.findById(userId);
        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found"));
    }


    public Cart removeProductFromCart(Integer userId, Integer productId) {
        Cart cart = findCartByUserId(userId);
        Product product = productService.findById(productId);
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItemRepository.delete(cartItem);
        return cart;
    }

    public Cart addProductToCart(Integer userId, Integer productId) {

        Cart cart = findCartByUserId(userId);
        CartItem cartItem = null;
        for(CartItem cartItemFromList:cart.getCartItems()){
            if(cartItemFromList.getProduct().getId().equals(productId)){
                cartItem = cartItemFromList;
                cartItem.setQuantity(cartItem.getQuantity()+1);
                break;
            }
        }
        if(cartItem==null){
            Product product = productService.findById(productId);
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setQuantity(1);
            cartItem.setProduct(product);

        }
        cartItemRepository.save(cartItem);
        return cart;
    }

    public Cart findCartByUserId(Integer userId) {
        Optional<Cart> optionalCart = cartRepository.findById(userId);
        return optionalCart.orElse(null);
    }

    public void clearCart(Cart cart) {
        cartItemRepository.deleteAll(cart.getCartItems());
    }


    // Other methods for managing the cart entity
}
