package dev.sarvesh.productservice.models;

import lombok.Getter;

@Getter
public class Product extends BaseModel {

    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;

}
