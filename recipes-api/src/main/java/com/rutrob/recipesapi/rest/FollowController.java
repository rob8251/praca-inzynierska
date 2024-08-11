package com.rutrob.recipesapi.rest;

import com.rutrob.recipesapi.rest.dto.request.FollowRequest;
import com.rutrob.recipesapi.services.FollowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes-api/v1/follows")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public void createFollow(@RequestBody @Valid FollowRequest followRequest, Authentication authentication) {
        followService.createFollow(followRequest, authentication);
    }

    @DeleteMapping
    public void deleteFollow(@RequestBody @Valid FollowRequest followRequest, Authentication authentication) {
        followService.deleteFollow(followRequest, authentication);
    }
}
