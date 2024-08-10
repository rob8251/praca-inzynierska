package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // TODO: add filter methods, findAll is not good for performance
}
