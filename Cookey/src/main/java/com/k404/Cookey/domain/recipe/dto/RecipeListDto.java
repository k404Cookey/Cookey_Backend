package com.k404.Cookey.domain.recipe.dto;

import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.user.entity.User;

import lombok.Getter;

@Getter
public class RecipeListDto {
	private Long id;
	private String title;
	private String description;
	private int step;
	private int time;
	private int viewCount;
	private String thumnail;
	private User user;

	public RecipeListDto(Recipe recipe) {
		this.id = recipe.getId();
		this.title = recipe.getTitle();
		this.description = recipe.getDescription();
		this.step = recipe.getStep();
		this.time = recipe.getTime();
		this.viewCount = recipe.getViewCount();
		this.thumnail = recipe.getThumbnail();
		this.user = recipe.getUser();
	}
}
