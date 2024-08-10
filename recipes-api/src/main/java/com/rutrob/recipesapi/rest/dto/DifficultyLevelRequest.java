package com.rutrob.recipesapi.rest.dto;

import jakarta.validation.constraints.NotNull;

public record DifficultyLevelRequest(
        @NotNull Integer level,
        String name,
        String description
) {
}