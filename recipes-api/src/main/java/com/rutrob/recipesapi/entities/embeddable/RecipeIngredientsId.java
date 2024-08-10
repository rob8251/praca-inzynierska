package com.rutrob.recipesapi.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientsId implements Serializable {

    private Long recipeId;

    private Long ingredientId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RecipeIngredientsId that = (RecipeIngredientsId) o;
        return getRecipeId() != null && Objects.equals(getRecipeId(), that.getRecipeId())
                && getIngredientId() != null && Objects.equals(getIngredientId(), that.getIngredientId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }
}
