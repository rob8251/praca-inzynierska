package com.rutrob.recipesapi.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record IngredientRequest(@NotBlank String name) {
}