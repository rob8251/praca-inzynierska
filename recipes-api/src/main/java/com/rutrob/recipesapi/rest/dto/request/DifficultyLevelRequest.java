package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotNull;

public record DifficultyLevelRequest(
        @NotNull Integer level,
        String name,
        String description
) {
}