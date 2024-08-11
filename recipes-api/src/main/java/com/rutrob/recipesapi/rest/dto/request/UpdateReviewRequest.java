package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateReviewRequest(
        @NotNull Integer rating,
        @NotBlank String comment
) {
}
