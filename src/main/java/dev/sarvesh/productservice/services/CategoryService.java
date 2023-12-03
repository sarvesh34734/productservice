package dev.sarvesh.productservice.services;

import dev.sarvesh.productservice.dtos.CategoryDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(UUID id) throws NotFoundException;

}
