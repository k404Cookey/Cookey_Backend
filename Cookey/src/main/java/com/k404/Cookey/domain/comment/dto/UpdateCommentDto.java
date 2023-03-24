package com.k404.Cookey.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateCommentDto {
    private String content;
    private String imageUrl;
}
