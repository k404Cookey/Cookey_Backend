package com.k404.Cookey.domain.recipe.service;

import lombok.RequiredArgsConstructor;
import com.k404.Cookey.domain.comment.repository.CommentRepository;
import com.k404.Cookey.domain.common.YesOrNo;
import com.k404.Cookey.domain.ingredient.entity.Ingredient;
import com.k404.Cookey.domain.ingredient.repository.IngredientRepository;
import com.k404.Cookey.domain.label.repository.LabelRepository;
import com.k404.Cookey.domain.rating.repository.RatingRepository;
import com.k404.Cookey.domain.recipe.dto.RequestDto;
import com.k404.Cookey.domain.recipe.dto.UpdateRequestDto;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.recipe.repository.RecipeDslRepository;
import com.k404.Cookey.domain.recipe.repository.RecipeRepository;
import com.k404.Cookey.domain.recipe_theme.entity.RecipeTheme;
import com.k404.Cookey.domain.recipe_theme.repository.RecipeThemeRepository;
import com.k404.Cookey.domain.step.entity.Step;
import com.k404.Cookey.domain.step.repository.StepRepository;
import com.k404.Cookey.domain.theme.entity.Theme;
import com.k404.Cookey.domain.theme.repository.ThemeRepository;
import com.k404.Cookey.domain.user.entity.User;
import com.k404.Cookey.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeDslRepository recipeDslRepository;

    private final IngredientRepository ingredientRepository;

    private final RecipeThemeRepository recipeThemeRepository;
    private final ThemeRepository themeRepository;

    private final StepRepository stepRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;
    private final LabelRepository labelRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long add(RequestDto requestDto){

        User user = userRepository.getOne(requestDto.getWriterId());

        List<Theme> themes = themeRepository.findAllById(requestDto.getThemeIds());
        List<RecipeTheme> recipeThemes = themes.stream()
                .map(t-> RecipeTheme.builder()
                        .theme(t)
                        .build())
                .collect(Collectors.toList());

        //recipeThemeRepository.saveAll(recipeThemes);

        List<Ingredient> ingredients= requestDto.getMainIngredients().stream()
                .map((i)->Ingredient.builder()
                        .name(i.getName())
                        .isSub(YesOrNo.N)
                        .build())
                .collect(Collectors.toList());


        if(requestDto.getSubIngredients() != null){
            List<Ingredient> subIngredients = requestDto.getSubIngredients().stream()
                    .map((i)->Ingredient.builder()
                            .name(i.getName())
                            .isSub(YesOrNo.Y)
                            .build())
                    .collect(Collectors.toList());

            ingredients.addAll(subIngredients);
        }


        AtomicInteger sequence = new AtomicInteger();
        List<Step> steps = requestDto.getSteps().stream()
                .map((s)->Step.builder()
                        .description(s.getDescription())
                        .imageUrl(s.getImageUrl())
                        .sequence(sequence.getAndIncrement())
                        .build())
                .collect(Collectors.toList());


        Recipe recipe = Recipe.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .step(requestDto.getSteps().size())
                .time(requestDto.getTime())
                .thumbnail(requestDto.getThumbnail())
                .user(user)
                .ingredients(ingredients)
                .recipeThemes(recipeThemes)
                .steps(steps)
                .build();

        if(requestDto.getPid() != null){
            Recipe parentRecipe = recipeRepository.findById(requestDto.getPid()).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + requestDto.getPid()));
            recipe.setParent(parentRecipe);
        }
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    @Transactional
    public Long update(Long id, UpdateRequestDto updateRequestDto){

        //recipe 수정
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + id));


        List<Theme> themes = themeRepository.findAllById(updateRequestDto.getThemeIds());
        List<RecipeTheme> recipeThemes = themes.stream()
                .map(t-> RecipeTheme.builder()
                        .theme(t)
                        .build())
                .collect(Collectors.toList());


        //step update
        updateRequestDto.getSteps().forEach(stepDto -> {

            Step step = stepRepository.getOne(stepDto.getId());
            step.update(recipe , stepDto.getDescription(), stepDto.getImageUrl(), stepDto.getSequence());
        });

        //delete ingredients
        ingredientRepository.deleteByRecipeId(id);

        List<Ingredient> ingredients= updateRequestDto.getMainIngredients().stream()
                .map((i)->Ingredient.builder()
                        .name(i.getName())
                        .isSub(YesOrNo.N)
                        .build())
                .collect(Collectors.toList());


        if(updateRequestDto.getSubIngredients() != null){
            List<Ingredient> subIngredients = updateRequestDto.getSubIngredients().stream()
                    .map((i)->Ingredient.builder()
                            .name(i.getName())
                            .isSub(YesOrNo.Y)
                            .build())
                    .collect(Collectors.toList());

            ingredients.addAll(subIngredients);
        }

        if(updateRequestDto.getPid() == null){
            recipe.update(updateRequestDto.getTitle(),
                    updateRequestDto.getDescription(),
                    updateRequestDto.getSteps().size(),
                    updateRequestDto.getTime(),
                    updateRequestDto.getViewCount(),
                    updateRequestDto.getWishCount(),
                    updateRequestDto.getThumbnail(),
                    ingredients,
                    recipeThemes,
                    null
            );
            return id;
        }

        Recipe parentRecipe = recipeRepository.findById(updateRequestDto.getPid()).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + updateRequestDto.getPid()));

        recipe.update(updateRequestDto.getTitle(),
                updateRequestDto.getDescription(),
                updateRequestDto.getSteps().size(),
                updateRequestDto.getTime(),
                updateRequestDto.getViewCount(),
                updateRequestDto.getWishCount(),
                updateRequestDto.getThumbnail(),
                ingredients,
                recipeThemes,
                parentRecipe
        );

        return id;
    }

    @Transactional
    public void delete(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + id));
        recipeRepository.delete(recipe);
    }

    @Transactional
    public Recipe get(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + id));

        return recipe;
    }


    @Transactional
    public void updateStarAverage (Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + id));
        Double starAverage = recipeDslRepository.getAverageStar(id).get(0);

        recipe.updateStarCount(starAverage);
    }

    @Transactional
    public void incViewCount(Recipe recipe){
        recipe.incViewCount();
    }
   
    @Transactional
    public void incWishCount(Recipe recipe){
        recipe.incWishCount();
    }
    
    @Transactional
    public void decWishCount(Recipe recipe){
        recipe.decWishCount();

    }
}
