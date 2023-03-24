package com.k404.Cookey.domain.ingredient.repository;

import com.k404.Cookey.domain.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Long deleteByRecipeId(Long recipeId);
}
