package com.k404.Cookey.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RequestCommentDto {
    private String content;
    private String imageUrl;
    private Long pid;
    private Long recipeId;
    private Long userId;
    
  //기본 생성자 추가
    public RequestCommentDto() {
    }
}

