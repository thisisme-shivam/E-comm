package com.example.Ecomm.controller;

import com.example.Ecomm.model.Product;
import com.example.Ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);

    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        boolean isDeleted =  productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.ok("Deleted Successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
