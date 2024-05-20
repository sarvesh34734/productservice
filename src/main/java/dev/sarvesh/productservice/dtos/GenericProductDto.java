package dev.sarvesh.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class GenericProductDto implements Serializable {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
