package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record IngredientRequest(@NotBlank String name) {
}