package com.k404.Cookey.domain.label.controller;

import java.security.Principal;

import com.k404.Cookey.domain.common.Result;
import com.k404.Cookey.domain.common.ResultList;
import com.k404.Cookey.domain.label.service.LabelService;
import com.k404.Cookey.domain.monthly_label.service.MonthlyLabelService;
import com.k404.Cookey.domain.recipe.dto.ResponseDto;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.recipe.service.RecipeService;
import com.k404.Cookey.domain.user.entity.User;
import com.k404.Cookey.domain.user.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LabelController {

	private final RecipeService recipeService;
	private final LabelService labelService;
	private final MonthlyLabelService monthlyLabelService;

	@PostMapping("/recipes/{id}/label")
	public Result<Boolean> add(@PathVariable Long id, Principal principal) {
		Recipe recipe = recipeService.get(id);
		User user = (User) ((Authentication) principal).getPrincipal();
		labelService.add(user, recipe);
		recipeService.incWishCount(recipe);
		monthlyLabelService.label(id);
		return new Result<Boolean>(true);
	}

	@DeleteMapping("/recipes/{id}/label")
	public Result<Boolean> delete(@PathVariable Long id, Principal principal) {
		Recipe recipe = recipeService.get(id);
		User user = (User) ((Authentication) principal).getPrincipal();		
		labelService.delete(user, recipe);
		recipeService.decWishCount(recipe);
		monthlyLabelService.unlabel(id);
		return new Result<Boolean>(true);
	}

	@GetMapping("/label")
	public ResultList<ResponseDto> labelList(Principal principal,
			@RequestParam(required = false, defaultValue = "10") int limit,
			@RequestParam(required = false, defaultValue = "0") int offset) {
		User user = (User) ((Authentication) principal).getPrincipal();		
		return new ResultList<ResponseDto>(labelService.labelList(user, limit, offset));
	}

}
