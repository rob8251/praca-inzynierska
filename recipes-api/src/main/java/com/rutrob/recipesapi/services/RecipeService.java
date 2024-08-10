package com.rutrob.recipesapi.services;

import com.rutrob.recipesapi.RecipeDto;
import com.rutrob.recipesapi.entities.Recipe;
import com.rutrob.recipesapi.entities.RecipeIngredients;
import com.rutrob.recipesapi.entities.Step;
import com.rutrob.recipesapi.mappers.RecipeMapper;
import com.rutrob.recipesapi.repositories.*;
import com.rutrob.recipesapi.rest.dto.RecipeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final StepRepository stepRepository;
    private final IngredientRepository ingredientRepository;
    private final MeasurementRepository measurementRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;

    public void createRecipe(RecipeRequest recipeRequest, Long userId) {
        Recipe recipe = recipeMapper.toEntity(recipeRequest);
        recipe.setName(recipeRequest.name());
        recipe.setDescription(recipeRequest.description());
        recipe.setPrepTimeMinutes(recipeRequest.prepTimeMinutes());
        recipe.setServings(recipeRequest.servings());
        recipe.setUser(userRepository.getReferenceById(userId));
        recipe.setCategory(categoryRepository.getReferenceById(recipeRequest.categoryId()));

        Recipe savedRecipe = recipeRepository.save(recipe);

        AtomicInteger stepNumber = new AtomicInteger(1);
        recipeRequest.steps().forEach(step -> {
            Step stepEntity = new Step();
            stepEntity.setRecipe(savedRecipe);
            stepEntity.setStepNumber(stepNumber.getAndIncrement());
            stepEntity.setDescription(step.description());
            stepRepository.save(stepEntity);
        });

        recipeRequest.ingredients().forEach(ingredientDetails -> {
            Long ingredientId = ingredientDetails.ingredientId();
            Long measurementId = ingredientDetails.measurementId();
            Float amount = ingredientDetails.amount();

            RecipeIngredients recipeIngredients = new RecipeIngredients(savedRecipe,
                    ingredientRepository.getReferenceById(ingredientId),
                    measurementRepository.getReferenceById(measurementId),
                    amount);

            recipeIngredientsRepository.save(recipeIngredients);
        });

    }

    public RecipeDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
        Set<RecipeDto.StepDto> steps = new HashSet<>();
        Set<RecipeDto.RecipeIngredientsDto> ingredients = new HashSet<>();

        recipe.getSteps().forEach(step ->
                steps.add(new RecipeDto.StepDto(step.getStepNumber(), step.getDescription())));

        recipe.getIngredients().forEach(ingredient ->
                ingredients.add(new RecipeDto.RecipeIngredientsDto(ingredient.getId(),
                        ingredient.getAmount(), ingredient.getMeasurement().getId(), ingredient.getMeasurement().getAbbreviation(),
                        ingredient.getIngredient().getId(), ingredient.getIngredient().getName())));

        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getPrepTimeMinutes(),
                recipe.getServings(),
                recipe.getImageUrl(),
                ingredients,
                steps,
                recipe.getCategory(),
                recipe.getUser().getId(),
                recipe.getCreatedAt(),
                recipe.getLastModifiedAt()
        );
    }

    public void updateRecipe(Long id, RecipeRequest recipeRequest, Long userId) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
        if (!Objects.equals(recipe.getUser().getId(), userId)) {
            throw new RuntimeException("User is not the owner of the recipe");
        }

        recipe.setName(recipeRequest.name());
        recipe.setDescription(recipeRequest.description());
        recipe.setPrepTimeMinutes(recipeRequest.prepTimeMinutes());
        recipe.setServings(recipeRequest.servings());
        recipe.setCategory(categoryRepository.getReferenceById(recipeRequest.categoryId()));

        recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id, Long userId) {
        if (!Objects.equals(recipeRepository.getReferenceById(id).getUser().getId(), userId)) {
            throw new RuntimeException("User is not the owner of the recipe");
        } else {
            stepRepository.deleteByRecipeId(id);
            recipeIngredientsRepository.deleteByRecipeId(id);
            recipeRepository.deleteById(id);
        }
    }

    public Page<RecipeDto> getRecipes(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeRepository.findAll(pageRequest);
        return recipes.map(recipeMapper::toDto);
    }

    public List<RecipeDto> getRecipesByIngredients(List<String> ingredientNames, Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return recipeRepository.findRecipesByIngredients(ingredientNames, pageRequest).stream()
                .map(recipeMapper::toDtoListElement)
                .toList();
    }
}
