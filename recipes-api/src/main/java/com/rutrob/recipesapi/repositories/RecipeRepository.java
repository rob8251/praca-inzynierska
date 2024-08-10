package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, CustomRecipeRepository {
}
