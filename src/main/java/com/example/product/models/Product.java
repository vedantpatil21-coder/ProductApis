package com.example.product.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private long id;
    private String name;
    private String discription;
    private int price;
    private String image;
    private Category category;
}
