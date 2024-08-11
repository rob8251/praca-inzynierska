package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StepDetails(@NotBlank String description) {
}
