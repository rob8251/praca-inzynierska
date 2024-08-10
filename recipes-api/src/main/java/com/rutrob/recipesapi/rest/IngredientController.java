package com.rutrob.recipesapi.rest;

import com.rutrob.recipesapi.rest.dto.IngredientRequest;
import com.rutrob.recipesapi.rest.dto.IngredientDto;
import com.rutrob.recipesapi.services.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes-api/v1/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public void createIngredient(@Valid @RequestBody IngredientRequest ingredientRequest) {
        ingredientService.createIngredient(ingredientRequest);
    }

    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
}
