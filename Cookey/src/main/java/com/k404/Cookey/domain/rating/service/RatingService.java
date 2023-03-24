package com.k404.Cookey.domain.rating.service;

import lombok.RequiredArgsConstructor;
import com.k404.Cookey.domain.rating.dto.RequestRatingDto;
import com.k404.Cookey.domain.rating.entity.Rating;
import com.k404.Cookey.domain.rating.repository.RatingRepository;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.recipe.repository.RecipeRepository;
import com.k404.Cookey.domain.user.entity.User;
import com.k404.Cookey.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;


    @Transactional
    public Long add(Long recipeId, Long userId, double star){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + recipeId));
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("해당 아이디의 유저가 없습니다. id : " + userId));
        Rating rating = Rating.builder().user(user).recipe(recipe).star(star).build();



        return ratingRepository.save(rating).getId();
    }

    @Transactional
    public Rating get(Long id){
        Rating rating = ratingRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 rating 이없습니다. id : " + id));

        return rating;
    }

}
