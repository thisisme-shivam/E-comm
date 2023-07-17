package com.example.Ecomm.service;

import com.example.Ecomm.model.Order;
import com.example.Ecomm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private  OrderRepository orderRepository;


    public Order placeOrder(Order order) {
        Order  ordered = orderRepository.save(order);
        return ordered;
    }

    public Order findOrderById(Integer userId){
        return orderRepository.findByUserId(userId);
    }
}
