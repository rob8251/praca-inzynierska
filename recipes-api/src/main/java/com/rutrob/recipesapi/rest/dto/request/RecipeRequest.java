package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecipeRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull String prepTimeMinutes,
        @NotNull Integer servings,
        @NotNull Long categoryId,
        List<IngredientDetails> ingredients,
        List<StepDetails> steps
) {
}