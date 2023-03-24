package com.k404.Cookey.domain.recipe.dto;

import lombok.Data;
import com.k404.Cookey.domain.ingredient.entity.Ingredient;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.step.entity.Step;
import com.k404.Cookey.domain.theme.entity.Theme;
import com.k404.Cookey.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseDto {
    private Long id;
    private String title;
    private String description;
    private String thumbnail;

    private List<IngredientDto> mainIngredients;
    private List<IngredientDto> subIngredients;
    private List<ThemeDto> themes;
    private List<StepDto> steps;

    private int time;
    private double starCount;
    private int wishCount;
    private int viewCount;
    private UserDto writer;
    private ParentRecipeDto parentRecipe;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public ResponseDto(Recipe recipe){
        id = recipe.getId();
        title = recipe.getTitle();
        description = recipe.getDescription();
        thumbnail = recipe.getThumbnail();

        mainIngredients = recipe.getMainIngredients().stream()
                .map(i->new IngredientDto(i.getName()))
                .collect(Collectors.toList());
        subIngredients = recipe.getSubIngredients().stream()
                .map(i->new IngredientDto(i.getName()))
                .collect(Collectors.toList());

        themes = recipe.getThemes().stream()
                .map(t->new ThemeDto(t.getId(),t.getName()))
                .collect(Collectors.toList());

        steps = recipe.getSteps().stream()
                .map(s->new StepDto(s.getId(), s.getDescription(),s.getImageUrl(),s.getSequence()))
                .collect(Collectors.toList());

        time = recipe.getTime();
        starCount= recipe.getStarCount();
        wishCount = recipe.getWishCount();
        viewCount = recipe.getViewCount();

        User user = recipe.getUser();
        writer = new UserDto(user.getId(),user.getName(), user.getEmail(), user.getImageUrl());
        parentRecipe = new ParentRecipeDto(recipe);

        createDate = recipe.getCreatedDate();
        modifiedDate = recipe.getModifiedDate();
    }


}
