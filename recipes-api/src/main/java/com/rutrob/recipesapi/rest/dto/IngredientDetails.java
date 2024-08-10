package com.rutrob.recipesapi.rest.dto;

import jakarta.validation.constraints.NotNull;

public record IngredientDetails(
        @NotNull Long ingredientId,
        @NotNull Float amount,
        @NotNull Long measurementId
) {
}
