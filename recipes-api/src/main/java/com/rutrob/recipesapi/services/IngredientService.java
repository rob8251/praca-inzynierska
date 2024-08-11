package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.rest.dto.request.IngredientRequest;
import com.rutrob.recipesapi.mappers.IngredientMapper;
import com.rutrob.recipesapi.repositories.IngredientRepository;
import com.rutrob.recipesapi.rest.dto.response.IngredientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public List<IngredientDto> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::toIngredientDto)
                .toList();
    }

    public void createIngredient(IngredientRequest ingredientRequest) {
        ingredientRepository.save(ingredientMapper.toEntity(ingredientRequest));
    }
}
