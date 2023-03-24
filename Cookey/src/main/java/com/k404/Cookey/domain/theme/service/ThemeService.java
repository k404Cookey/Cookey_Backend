package com.k404.Cookey.domain.theme.service;

import java.util.List;
import java.util.stream.Collectors;

import com.k404.Cookey.domain.theme.dto.ThemeDto;
import com.k404.Cookey.domain.theme.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ThemeService {
	private final ThemeRepository themeRepository;

	public List<ThemeDto> getAllTheme() {
		return themeRepository.findAll().stream().map(ThemeDto::new).collect(Collectors.toList());
	}

}
