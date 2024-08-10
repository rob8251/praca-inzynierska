package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomRecipeRepository {

    Page<Recipe> findRecipesByIngredients(List<String> ingredientNames, Pageable pageable);
}
