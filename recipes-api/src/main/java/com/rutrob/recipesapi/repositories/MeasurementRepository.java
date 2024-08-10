package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
