package com.rutrob.recipesapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String prepTimeMinutes; // should be an integer?

    private Integer servings;

    private String imageUrl;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredients> ingredients;

    @OneToMany(mappedBy = "recipe")
    private Set<Step> steps;

    @ManyToOne
    private Category category;

    @ManyToOne
    private DifficultyLevel difficultyLevel;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "recipe")
    private Set<Review> reviews;
    
    private Integer numberOfReviews;

    private Integer sumOfReviews;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastModifiedAt;

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
        Recipe recipe = (Recipe) o;
        return getId() != null && Objects.equals(getId(), recipe.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
