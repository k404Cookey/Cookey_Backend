package com.k404.Cookey.domain.comment.dto;

import lombok.Data;
import com.k404.Cookey.domain.comment.entity.Comment;
import com.k404.Cookey.domain.common.BaseTimeEntity;
import com.k404.Cookey.domain.recipe.dto.UserDto;
import com.k404.Cookey.domain.user.entity.User;

@Data
public class ResponseCommentDto extends BaseTimeEntity {
    private Long id;
    private String content;
    private String imageUrl;
    private UserDto writer;
    private Long pid;

    public ResponseCommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.imageUrl = comment.getImageUrl();

        User user = comment.getUser();
        this.writer = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getImageUrl());

        if(comment.getParent() != null) this.pid = comment.getParent().getId();

        this.setCreatedDate(comment.getCreatedDate());
        this.setModifiedDate(comment.getModifiedDate());
    }
}
