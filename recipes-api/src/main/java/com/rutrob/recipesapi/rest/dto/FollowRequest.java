package com.rutrob.recipesapi.rest.dto;

import jakarta.validation.constraints.NotNull;

public record FollowRequest(
        @NotNull Long followedId
) {
}
