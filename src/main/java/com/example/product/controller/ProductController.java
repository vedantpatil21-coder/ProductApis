package com.example.product.controller;

import com.example.product.dtos.ProductRequestSDto;
import com.example.product.models.Product;
import com.example.product.services.IProductService;
import com.example.product.services.InavlidProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<ProductWrapper> getProduct(@PathVariable("id") Long id){
        ResponseEntity<ProductWrapper> response;
        try {
            Product singleProduct = productService.getSingleProduct(id);
            ProductWrapper productWrapper = new ProductWrapper(singleProduct,"Successfully reterive the data");
            response= new ResponseEntity<>(productWrapper, HttpStatus.OK);


        }catch (InavlidProductException e){
            ProductWrapper productWrapper = new ProductWrapper(null,"Data not present");
            response = new ResponseEntity<>(productWrapper,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return response;


        //Product singleProduct = productService.getSingleProduct(id);
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
