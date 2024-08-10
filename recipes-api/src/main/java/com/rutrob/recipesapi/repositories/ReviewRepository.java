package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.recipe.id = :recipeId")
    List<Review> findByRecipeId(@Param("recipeId") Long recipeId);
}
