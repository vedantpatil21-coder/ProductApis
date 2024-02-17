package com.example.product.services;

import com.example.product.dtos.ProductRequestSDto;
import com.example.product.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IProductService {
    public Product getSingleProduct(Long id) throws InavlidProductException;

    List<Product> getAllProducts();

    Product updateProduct(Long id, ProductRequestSDto productRequestSDto);
}
