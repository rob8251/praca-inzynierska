package com.rutrob.recipesapi.rest.dto.response;

public record ReviewDto(
        Long id,
        String username,
        Integer rating,
        String comment
) {
}
