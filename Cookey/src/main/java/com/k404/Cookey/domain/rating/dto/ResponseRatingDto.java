package com.k404.Cookey.domain.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.k404.Cookey.domain.rating.entity.Rating;
import com.k404.Cookey.domain.recipe.dto.UserDto;
import com.k404.Cookey.domain.user.entity.User;

@Data

public class ResponseRatingDto {
    private Long id;
    private UserDto writer;
    private double star;

    public ResponseRatingDto(Rating rating){
        this.id = rating.getId();

        User user = rating.getUser();
        this.writer = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getImageUrl());
        this.star = rating.getStar();
    }
}
