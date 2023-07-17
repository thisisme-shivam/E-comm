package com.example.Ecomm.repository;

import com.example.Ecomm.model.Order;
import org.h2.jdbc.JdbcPreparedStatementBackwardsCompat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findByUserId(Integer userId);
}
