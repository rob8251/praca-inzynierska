package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Step;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StepRepository extends JpaRepository<Step, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Step s WHERE s.recipe.id = :id")
    void deleteByRecipeId(@Param("id") Long id);
}
