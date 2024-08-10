package com.rutrob.recipesapi.mappers;

import com.rutrob.recipesapi.entities.Measurement;
import com.rutrob.recipesapi.rest.dto.MeasurementDto;
import org.mapstruct.Mapper;

@Mapper
public interface MeasurementMapper {

    MeasurementDto toDto(Measurement measurement);
}
