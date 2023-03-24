package com.k404.Cookey.domain.recipe.repository;

import com.k404.Cookey.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
