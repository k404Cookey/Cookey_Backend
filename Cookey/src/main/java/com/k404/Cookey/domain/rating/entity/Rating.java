package com.k404.Cookey.domain.rating.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import com.k404.Cookey.domain.common.BaseTimeEntity;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
public class Rating extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "recipe_id", nullable = false)
	private Recipe recipe;

	private double star;

	@Builder
	public Rating(Recipe recipe,User user,double star){
		this.recipe = recipe;
		this.user = user;
		this.star = star;
	}
	public void  setRecipe(Recipe recipe){
		this.recipe =recipe;
	}
}
