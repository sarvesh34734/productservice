package dev.sarvesh.productservice.services;

import dev.sarvesh.productservice.dtos.CategoryDto;
import dev.sarvesh.productservice.dtos.GenericProductDto;
import dev.sarvesh.productservice.models.Category;
import dev.sarvesh.productservice.models.Product;

import java.util.List;

public class CategoryPopulator {

    public static CategoryDto populate(Category category){
        CategoryDto dto = new CategoryDto();

        dto.setName(category.getName());
        List<GenericProductDto> products = category.getProducts()
                .stream()
                .map(CategoryPopulator::populateProductData)
                .toList();
        dto.setProducts(products);
        return dto;
    }

    private static GenericProductDto populateProductData(Product product){
        GenericProductDto dto = new GenericProductDto();
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setId(product.getUuid());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice().getPrice());
        return dto;
    }

}
