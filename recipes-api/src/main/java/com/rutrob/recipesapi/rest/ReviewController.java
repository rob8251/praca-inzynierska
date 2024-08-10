package com.rutrob.recipesapi.rest;

import com.rutrob.recipesapi.rest.dto.CreateReviewRequest;
import com.rutrob.recipesapi.rest.dto.ReviewDto;
import com.rutrob.recipesapi.rest.dto.UpdateReviewRequest;
import com.rutrob.recipesapi.security.SecurityUser;
import com.rutrob.recipesapi.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes-api/v1/recipes/{recipeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public void createReview(@RequestBody @Valid CreateReviewRequest reviewRequest, Authentication authentication) {
        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();
        reviewService.createReview(reviewRequest, userId);
    }

    @GetMapping
    public List<ReviewDto> getReviews(@PathVariable Long recipeId) {
        return reviewService.getReviews(recipeId);
    }

    @PatchMapping("/{reviewId}")
    public ReviewDto updateReview(
            @PathVariable Long recipeId,
            @PathVariable Long reviewId,
            @RequestBody @Valid UpdateReviewRequest updateReviewRequest,
            Authentication authentication
    ) {
        return reviewService.updateReview(recipeId, reviewId, updateReviewRequest, authentication);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long recipeId, @PathVariable Long reviewId, Authentication authentication
    ) {
        reviewService.deleteReview(recipeId, reviewId, authentication);
    }

}
