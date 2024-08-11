package com.rutrob.recipesapi.rest;

import com.rutrob.recipesapi.rest.dto.request.DifficultyLevelRequest;
import com.rutrob.recipesapi.services.DifficultyLevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes-api/v1/difficulty-levels")
public class DifficultyLevelController {

    private final DifficultyLevelService difficultyLevelService;

    @PostMapping
    public void createDifficultyLevel(@Valid @RequestBody DifficultyLevelRequest difficultyLevelRequest) {
        difficultyLevelService.createDifficultyLevel(difficultyLevelRequest);
    }
}
