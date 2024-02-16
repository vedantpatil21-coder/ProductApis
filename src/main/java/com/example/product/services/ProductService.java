package com.example.product.services;

import com.example.product.dtos.ProductRequestSDto;
import com.example.product.dtos.ProductResponseDto;
import com.example.product.models.Category;
import com.example.product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ProductService implements IProductService {
    @Autowired
    RestTemplate restTemplate;

    public Product getProductFromResponseDto(ProductResponseDto responseDto){
        Product product = new Product();
        product.setId(responseDto.getId());
        product.setImage(responseDto.getImage());
        product.setPrice(responseDto.getPrice());
        product.setDiscription(responseDto.getDiscription());
        product.setName(responseDto.getTitle());
//        product.setCategory(new Category());
//        product.getCategory().setName(responseDto.getCategory());
        Category category = new Category();
        category.setName(responseDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {

        //https://fakestoreapi.com/products/1
        //RestTemplate restTemplate = new RestTemplate();
        ProductResponseDto responseObject = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductResponseDto.class);
        return getProductFromResponseDto(responseObject);
    }

    @Override
    public List<Product> getAllProducts() {

        ProductResponseDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products/", ProductResponseDto[].class);

        List<Product> output = new ArrayList<>();
        for(ProductResponseDto productResponseDto : response){
            output.add(getProductFromResponseDto(productResponseDto));
        }

        return output;
    }

    @Override
    public Product updateProduct(Long id, ProductRequestSDto productRequestSDto) {
        //restTemplate.put("https://fakestoreapi.com/products/"+id,ProductResponseDto.class);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestSDto, ProductResponseDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor = new HttpMessageConverterExtractor(ProductResponseDto.class, restTemplate.getMessageConverters());
        ProductResponseDto responseDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return getProductFromResponseDto(responseDto);
    }
}
