package com.k404.Cookey.domain.recipe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.k404.Cookey.domain.recipe.dto.ResponseDto;
import com.k404.Cookey.domain.recipe.repository.RecipeDslRepository;
import com.k404.Cookey.domain.user.entity.User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecipeListService {
	
	private final RecipeDslRepository recipeDslRepository;
	
	public List<ResponseDto> findAll(String stepStart, String stepEnd, String time, LocalDateTime start, LocalDateTime end, String order, String keyword, int limit, int offset) {		
		System.out.println("recipeDslRepository안 findAll 실행후 ");
		return recipeDslRepository.findAll(stepStart, stepEnd, time, start, end, order, keyword, limit, offset).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
	}
	
	public List<ResponseDto> findAllOrderByRecipes(String stepStart, String stepEnd, String time, LocalDateTime start, LocalDateTime end,  String keyword, int limit, int offset) {		
		System.out.println("recipeDslRepository안 findAll 실행후 ");
		return recipeDslRepository.findAllOrderByRecipes(stepStart, stepEnd, time, start, end, keyword, limit, offset).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
	}
	
	public List<ResponseDto> getUserRecipes(User user, int limit, int offset) {
		return recipeDslRepository.getUserRecipes(user, limit, offset).stream()
				.map(ResponseDto::new).collect(Collectors.toList());
	}
	
}
