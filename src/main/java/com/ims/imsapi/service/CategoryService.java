package com.ims.imsapi.service;

import com.ims.imsapi.dto.CategoryDto;
import com.ims.imsapi.model.Category;
import com.ims.imsapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findCategoryBySlug(slug);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<CategoryDto> getAllCategories() {
//        return categoryRepository.findAll();
        var categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return List.of();
        }
        return categories.stream()
                .map(Category::toCategoryDto)
                .collect(Collectors.toList());
    }
}
