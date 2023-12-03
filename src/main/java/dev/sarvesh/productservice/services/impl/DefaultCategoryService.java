package dev.sarvesh.productservice.services.impl;

import dev.sarvesh.productservice.dtos.CategoryDto;
import dev.sarvesh.productservice.exceptions.NotFoundException;
import dev.sarvesh.productservice.models.Category;
import dev.sarvesh.productservice.repositories.CategoryRepository;
import dev.sarvesh.productservice.services.CategoryPopulator;
import dev.sarvesh.productservice.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryPopulator::populate).toList();
    }

    @Override
    public CategoryDto getCategoryById(UUID id) throws NotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category by uuid - "+id+" not found");
        }
        return CategoryPopulator.populate(category.get());
    }
}
