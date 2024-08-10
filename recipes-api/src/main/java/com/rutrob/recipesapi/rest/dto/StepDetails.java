package com.rutrob.recipesapi.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record StepDetails(@NotBlank String description) {
}
