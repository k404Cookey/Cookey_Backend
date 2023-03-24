package com.k404.Cookey.domain.theme.dto;

import com.k404.Cookey.domain.theme.entity.Theme;

import lombok.Getter;

@Getter
public class ThemeDto {
	private Long id;
	private String name;

	public ThemeDto(Theme entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
}
