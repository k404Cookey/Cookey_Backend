package com.k404.Cookey.domain.ingredient.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.k404.Cookey.domain.common.YesOrNo;
import com.k404.Cookey.domain.recipe.entity.Recipe;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    private String name;

    @Enumerated(EnumType.STRING)
    private YesOrNo isSub;
    @Builder
    public Ingredient(String name,YesOrNo isSub){
        this.name = name;
        this.isSub = isSub;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
