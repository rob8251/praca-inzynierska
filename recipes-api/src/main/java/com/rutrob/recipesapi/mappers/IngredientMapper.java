package com.rutrob.recipesapi.mappers;

import com.rutrob.recipesapi.rest.dto.request.IngredientRequest;
import com.rutrob.recipesapi.entities.Ingredient;
import com.rutrob.recipesapi.rest.dto.response.IngredientDto;
import org.mapstruct.Mapper;

@Mapper
public interface IngredientMapper {

    Ingredient toEntity(IngredientRequest ingredientRequest);

    IngredientDto toIngredientDto(Ingredient ingredient);

}
