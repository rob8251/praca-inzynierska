package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotNull;

public record IngredientDetails(
        @NotNull Long ingredientId,
        @NotNull Float amount,
        @NotNull Long measurementId
) {
}
