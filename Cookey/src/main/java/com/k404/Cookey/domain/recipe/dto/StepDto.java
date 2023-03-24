package com.k404.Cookey.domain.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StepDto {
    private Long id;
    private String description;
    private String imageUrl;
    private int sequence;
}
