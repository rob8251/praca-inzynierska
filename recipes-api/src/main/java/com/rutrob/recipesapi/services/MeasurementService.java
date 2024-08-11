package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.mappers.MeasurementMapper;
import com.rutrob.recipesapi.repositories.MeasurementRepository;
import com.rutrob.recipesapi.rest.dto.response.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;

    public List<MeasurementDto> getAllMeasurements() {
        return measurementRepository.findAll().stream()
                .map(measurementMapper::toDto)
                .toList();
    }
}
