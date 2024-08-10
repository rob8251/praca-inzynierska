package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.RecipeIngredients;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM RecipeIngredients ri WHERE ri.recipe.id = :id")
    void deleteByRecipeId(@Param("id") Long id);
}
