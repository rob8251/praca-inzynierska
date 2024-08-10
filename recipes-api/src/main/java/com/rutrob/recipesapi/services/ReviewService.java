package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.entities.Recipe;
import com.rutrob.recipesapi.entities.Review;
import com.rutrob.recipesapi.mappers.ReviewMapper;
import com.rutrob.recipesapi.repositories.RecipeRepository;
import com.rutrob.recipesapi.repositories.ReviewRepository;
import com.rutrob.recipesapi.repositories.UserRepository;
import com.rutrob.recipesapi.rest.dto.ReviewDto;
import com.rutrob.recipesapi.rest.dto.CreateReviewRequest;
import com.rutrob.recipesapi.rest.dto.UpdateReviewRequest;
import com.rutrob.recipesapi.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository; // TODO: should be through UserService?
    private final ReviewMapper reviewMapper;

    public void createReview(CreateReviewRequest reviewRequest, Long userId) {
        // TODO: validate (review only once per user per recipe?)
        Review review = reviewMapper.toEntity(reviewRequest);
        Recipe recipe = recipeRepository.findById(reviewRequest.recipeId()).orElseThrow();
        recipe.setSumOfReviews(recipe.getSumOfReviews() + review.getRating());
        recipe.setNumberOfReviews(recipe.getNumberOfReviews() + 1);

        review.setRecipe(recipe);
        review.setUser(userRepository.getReferenceById(userId));

        recipeRepository.save(recipe);
        reviewRepository.save(review);
    }


    public List<ReviewDto> getReviews(Long recipeId) {
        return reviewRepository.findByRecipeId(recipeId).stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    public ReviewDto updateReview(Long recipeId, Long reviewId, UpdateReviewRequest updateReviewRequest,
                                  Authentication authentication) {

        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("Permission denied");
        }
        if (!review.getRecipe().getId().equals(recipeId)) {
            throw new RuntimeException("Review not found");
        }

        Recipe recipe = review.getRecipe();
        recipe.setSumOfReviews(recipe.getSumOfReviews() - review.getRating() + updateReviewRequest.rating());

        review.setComment(updateReviewRequest.comment());
        review.setRating(updateReviewRequest.rating());

        reviewRepository.save(review);
        recipeRepository.save(recipe);

        return reviewMapper.toDto(review);
    }

    public void deleteReview(Long recipeId, Long reviewId, Authentication authentication) {
        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("Permission denied");
        }

        if (!review.getRecipe().getId().equals(recipeId)) {
            throw new RuntimeException("Review not found");
        }

        Recipe recipe = review.getRecipe();
        recipe.setSumOfReviews(recipe.getSumOfReviews() - review.getRating());
        recipe.setNumberOfReviews(recipe.getNumberOfReviews() - 1);

        reviewRepository.delete(review);
        recipeRepository.save(recipe);
    }
}
