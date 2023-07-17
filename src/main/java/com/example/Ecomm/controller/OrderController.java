package com.example.Ecomm.controller;

import com.example.Ecomm.model.*;
import com.example.Ecomm.service.CartService;
import com.example.Ecomm.service.OrderService;
import com.example.Ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private  OrderService orderService;
    @Autowired
    private  CartService cartService;
    @Autowired
    private  UserService userService;

    @PostMapping("/place-order/{userId}")
    public Order placeOrder(@PathVariable Integer userId) {
        Cart cart = cartService.findCartByUserId(userId);
        User user = userService.findById(userId);
        // Create an order from the cart items
        Order order = new Order();
        order.setUser(user);

        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        Order placedOrder = orderService.placeOrder(order);
        cartService.clearCart(cart);

        return placedOrder;
    }

    @GetMapping("/getOrder/{userId}")
    public ResponseEntity<List<OrderItem>> findOrderById(@PathVariable Integer userId){
        Order order = orderService.findOrderById(userId);
        return ResponseEntity.ok(order.getOrderItems());
    }
}
