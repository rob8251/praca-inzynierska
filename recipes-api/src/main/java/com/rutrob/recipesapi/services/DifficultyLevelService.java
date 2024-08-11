package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.mappers.DifficultyLevelMapper;
import com.rutrob.recipesapi.repositories.DifficultyLevelRepository;
import com.rutrob.recipesapi.rest.dto.request.DifficultyLevelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DifficultyLevelService {

    private final DifficultyLevelRepository difficultyLevelRepository;
    private final DifficultyLevelMapper difficultyLevelMapper;

    public void createDifficultyLevel(DifficultyLevelRequest difficultyLevelRequest) {

        difficultyLevelRepository.save(difficultyLevelMapper
                .toEntity(difficultyLevelRequest));
    }
}
