package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotBlank String username,
        @NotEmpty char[] password
) {
}
