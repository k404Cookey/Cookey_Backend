package com.k404.Cookey.domain.theme.controller;

import com.k404.Cookey.domain.common.ResultList;
import com.k404.Cookey.domain.theme.dto.ThemeDto;
import com.k404.Cookey.domain.theme.service.ThemeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ThemeController {
	
	private final ThemeService themeService;
	
	@GetMapping("/theme")
	public ResultList<ThemeDto> getAllTheme(){
		return new ResultList<ThemeDto>(themeService.getAllTheme());
	}
}
