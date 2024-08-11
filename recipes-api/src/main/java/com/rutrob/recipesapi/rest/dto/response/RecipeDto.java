package com.rutrob.recipesapi.rest.dto.response;

import com.rutrob.recipesapi.entities.Category;
import com.rutrob.recipesapi.entities.embeddable.RecipeIngredientsId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.rutrob.recipesapi.entities.Recipe}
 */
public record RecipeDto(Long id, String name, String description, String prepTimeMinutes, Integer servings,
                        String imageUrl, Set<RecipeIngredientsDto> ingredients, Set<StepDto> steps, Category category,
                        Long userId, LocalDateTime createdAt, LocalDateTime lastModifiedAt) implements Serializable {

    public record RecipeIngredientsDto(RecipeIngredientsId id, Float amount, Long measurementId, String measurementAbbr,
                                       Long ingredientId, String ingredientName) implements Serializable {
    }

    public record StepDto(Integer stepNumber, String description) implements Serializable {
    }
}