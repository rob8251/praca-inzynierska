package com.rutrob.recipesapi.rest;

import com.rutrob.recipesapi.rest.dto.response.RecipeDto;
import com.rutrob.recipesapi.rest.dto.request.RecipeRequest;
import com.rutrob.recipesapi.security.SecurityUser;
import com.rutrob.recipesapi.services.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes-api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public void createRecipe(@Valid @RequestBody RecipeRequest recipeRequest, Authentication authentication) {
        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();
        recipeService.createRecipe(recipeRequest, userId);
    }

    @GetMapping("/{id}")
    public RecipeDto getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping
    public Page<RecipeDto> getRecipes(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return recipeService.getRecipes(page, size);
    }

    @GetMapping("/finder")
    public List<RecipeDto> getRecipesByIngredients(
            @RequestParam List<String> ingredientNames,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return recipeService.getRecipesByIngredients(ingredientNames, page, size);
    }


    @PutMapping("/{id}")
    public void updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeRequest recipeRequest, Authentication authentication) {
        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();
        recipeService.updateRecipe(id, recipeRequest, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id, Authentication authentication) {
        Long userId = ((SecurityUser) authentication.getPrincipal()).getUserId();
        recipeService.deleteRecipe(id, userId);
    }
}
