package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevel, Long> {
}
