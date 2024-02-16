package com.example.product.controller;

import com.example.product.dtos.ProductRequestSDto;
import com.example.product.models.Product;
import com.example.product.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    private List<Product> getAllProduct(){

        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    private Product getProduct(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @PostMapping("/products")
    private Product addProduct(@RequestBody ProductRequestSDto productRequestSDto){
        return new Product();
    }

    @PutMapping("/products/{id}")
    private Product updateProduct(@PathVariable("id") Long id,@RequestBody ProductRequestSDto productRequestSDto){
        return productService.updateProduct(id,productRequestSDto);
    }

    @DeleteMapping("/products/{id}")
    private boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }
}
