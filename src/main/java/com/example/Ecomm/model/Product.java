package com.example.Ecomm.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String brand;
    private String image;
    private double price;
}
