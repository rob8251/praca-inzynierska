package com.rutrob.recipesapi.mappers;

import com.rutrob.recipesapi.entities.Category;
import com.rutrob.recipesapi.rest.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    CategoryDto toDto(Category category);
}
