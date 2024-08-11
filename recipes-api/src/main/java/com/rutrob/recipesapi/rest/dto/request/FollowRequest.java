package com.rutrob.recipesapi.rest.dto.request;

import jakarta.validation.constraints.NotNull;

public record FollowRequest(
        @NotNull Long followedId
) {
}
