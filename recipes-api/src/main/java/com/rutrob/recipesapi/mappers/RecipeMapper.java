package com.rutrob.recipesapi.mappers;

import com.rutrob.recipesapi.rest.dto.response.RecipeDto;
import com.rutrob.recipesapi.entities.Recipe;
import com.rutrob.recipesapi.rest.dto.request.RecipeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RecipeMapper {

    @Mapping(target = "userId", source = "user.id")
    RecipeDto toDto(Recipe recipe);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "steps", ignore = true)
    @Mapping(target = "ingredients", ignore = true)
    RecipeDto toDtoListElement(Recipe recipe);
    Recipe toEntity(RecipeRequest recipeRequest);
}
