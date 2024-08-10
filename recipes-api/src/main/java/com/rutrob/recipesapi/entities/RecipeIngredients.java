package com.rutrob.recipesapi.entities;

import com.rutrob.recipesapi.entities.embeddable.RecipeIngredientsId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "recipe_ingredients")
public class RecipeIngredients {

    @EmbeddedId
    private RecipeIngredientsId id;

    private Float amount;

    @ManyToOne
    private Measurement measurement;

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;

    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;

    public RecipeIngredients(Recipe recipe, Ingredient ingredient, Measurement measurement, Float amount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.id = new RecipeIngredientsId(recipe.getId(), ingredient.getId());
        this.measurement = measurement;
        this.amount = amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                 : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RecipeIngredients that = (RecipeIngredients) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
