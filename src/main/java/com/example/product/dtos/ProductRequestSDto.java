package com.example.product.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductRequestSDto {

    private String title;
    private int price;
    private String discription;
    private String image;
    private String category;

}
