package com.rutrob.recipesapi.repositories;

import com.rutrob.recipesapi.entities.Ingredient;
import com.rutrob.recipesapi.entities.Recipe;
import com.rutrob.recipesapi.entities.RecipeIngredients;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomRecipeRepositoryImpl implements CustomRecipeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Recipe> findRecipesByIngredients(List<String> ingredientNames, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recipe> cq = builder.createQuery(Recipe.class);

        Root<Recipe> recipeRoot = cq.from(Recipe.class); // SELECT r FROM Recipe r

        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<RecipeIngredients> subqueryRoot = subquery.from(RecipeIngredients.class);
        Join<RecipeIngredients, Ingredient> subqueryIngredientJoin = subqueryRoot.join("ingredient");

        subquery.select(builder.literal(1L))
                .where(builder.and(
                        builder.equal(subqueryRoot.get("recipe"), recipeRoot),
                        subqueryIngredientJoin.get("name").in(ingredientNames).not()
                ));

        cq.select(recipeRoot).distinct(true)
                .where(builder.not(builder.exists(subquery)));

        //return entityManager.createQuery(cq).getResultList();
        List<Recipe> result = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Recipe> countRoot = countQuery.from(Recipe.class);

        Subquery<Long> countSubquery = cq.subquery(Long.class);
        Root<RecipeIngredients> countSubqueryRoot = countSubquery.from(RecipeIngredients.class);
        Join<RecipeIngredients, Ingredient> countSubqueryIngredientJoin = countSubqueryRoot.join("ingredient");

        countSubquery.select(builder.literal(1L))
                .where(builder.and(
                        builder.equal(countSubqueryRoot.get("recipe"), countRoot),
                        countSubqueryIngredientJoin.get("name").in(ingredientNames).not()
                ));

        countQuery.select(builder.count(countRoot)).distinct(true)
                .where(builder.not(builder.exists(countSubquery)));

        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(result, pageable, count);
    }
}
