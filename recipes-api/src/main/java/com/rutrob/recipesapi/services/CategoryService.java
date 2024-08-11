package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.mappers.CategoryMapper;
import com.rutrob.recipesapi.repositories.CategoryRepository;
import com.rutrob.recipesapi.rest.dto.response.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }
}
