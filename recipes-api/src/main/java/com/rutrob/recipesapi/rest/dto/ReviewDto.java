package com.rutrob.recipesapi.rest.dto;

public record ReviewDto(
        Long id,
        String username,
        Integer rating,
        String comment
) {
}
