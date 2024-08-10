package com.rutrob.recipesapi.rest;

import com.rutrob.recipesapi.rest.dto.MeasurementDto;
import com.rutrob.recipesapi.services.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes-api/v1/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    @GetMapping
    public List<MeasurementDto> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }
}
