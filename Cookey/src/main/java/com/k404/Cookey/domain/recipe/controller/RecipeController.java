package com.k404.Cookey.domain.recipe.controller;

import lombok.RequiredArgsConstructor;
import com.k404.Cookey.domain.comment.dto.ResponseCommentDto;
import com.k404.Cookey.domain.comment.entity.Comment;
import com.k404.Cookey.domain.comment.service.CommentService;
import com.k404.Cookey.domain.common.Result;
import com.k404.Cookey.domain.common.ResultList;
import com.k404.Cookey.domain.monthly_view.service.MonthlyViewService;
import com.k404.Cookey.domain.rating.dto.RequestRatingDto;
import com.k404.Cookey.domain.rating.dto.ResponseRatingDto;
import com.k404.Cookey.domain.rating.entity.Rating;
import com.k404.Cookey.domain.rating.service.RatingService;
import com.k404.Cookey.domain.recipe.dto.RequestDto;
import com.k404.Cookey.domain.recipe.dto.ResponseDto;
import com.k404.Cookey.domain.recipe.dto.UpdateRequestDto;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.recipe.service.RecipeService;
import com.k404.Cookey.domain.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final MonthlyViewService monthlyViewService;
    private final CommentService commentService;
    private final RatingService ratingService;
	
    @GetMapping("/{id}")
    public Result<ResponseDto> getById (@PathVariable Long id){
        Recipe recipe = recipeService.get(id);
        recipeService.incViewCount(recipe);
        ResponseDto responseDto = new ResponseDto(recipe);
        monthlyViewService.visit(id);
        return new Result<ResponseDto>(responseDto);
    }

    @GetMapping("/{id}/comments")
    public ResultList<ResponseCommentDto> getCommentsByRecipeId (
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "10") int limit){

        List<Comment> comments = commentService.findByRecipeId(id,offset,limit);

        return new ResultList<ResponseCommentDto>(comments.stream().map(comment -> new ResponseCommentDto(comment)).collect(Collectors.toList()));

    }



    @PostMapping("/{id}/ratings")
    public Result<ResponseRatingDto> add (@PathVariable Long id, @RequestBody Map<String, Double> rating, Principal principal){
        User user = (User) ((Authentication) principal).getPrincipal();
        Long ratingId = ratingService.add(id, user.getId(), rating.get("star"));

        recipeService.updateStarAverage(id);

        return new Result<ResponseRatingDto>(new ResponseRatingDto(ratingService.get(ratingId)));
    }


    @PutMapping("/{id}")
    public Result<ResponseDto> update (@PathVariable Long id, @RequestBody @Validated UpdateRequestDto reqDto){

        recipeService.update(id, reqDto);
        return new Result<ResponseDto>(new ResponseDto(recipeService.get(id)));
    }

    @PostMapping("")
    public Result<ResponseDto> add (@RequestBody @Validated RequestDto reqDto){
        Long id = recipeService.add(reqDto);
        return new Result<ResponseDto>(new ResponseDto(recipeService.get(id)));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete (@PathVariable Long id){
        recipeService.delete(id);
        return new Result<Boolean>(true);
    }
}
