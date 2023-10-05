package dev.sarvesh.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private String image;
    // Product : Category
    //     1   :    1
    //     m   :    1
    // final mapping - m : 1 - manyToOne
    @ManyToOne
    private Category category;
    private double price;

}
