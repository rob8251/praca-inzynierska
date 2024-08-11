package com.rutrob.recipesapi.mappers;

import com.rutrob.recipesapi.rest.dto.request.DifficultyLevelRequest;
import com.rutrob.recipesapi.entities.DifficultyLevel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface DifficultyLevelMapper {
    DifficultyLevel toEntity(DifficultyLevelRequest difficultyLevelRequest);

    DifficultyLevelRequest toDto(DifficultyLevel difficultyLevel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DifficultyLevel partialUpdate(DifficultyLevelRequest difficultyLevelRequest, @MappingTarget DifficultyLevel difficultyLevel);
}
