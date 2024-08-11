package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record SignUpRequest(
        @NotBlank String username,
        @NotEmpty char[] password,
        @NotBlank @Email String email,
        String firstName,
        String lastName
) {
}
