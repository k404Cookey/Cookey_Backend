package com.k404.Cookey.domain.comment.service;

import lombok.RequiredArgsConstructor;
import com.k404.Cookey.domain.comment.dto.RequestCommentDto;
import com.k404.Cookey.domain.comment.dto.ResponseCommentDto;
import com.k404.Cookey.domain.comment.dto.UpdateCommentDto;
import com.k404.Cookey.domain.comment.entity.Comment;
import com.k404.Cookey.domain.comment.repository.CommentDslRepository;
import com.k404.Cookey.domain.comment.repository.CommentRepository;
import com.k404.Cookey.domain.recipe.entity.Recipe;
import com.k404.Cookey.domain.recipe.repository.RecipeDslRepository;
import com.k404.Cookey.domain.recipe.repository.RecipeRepository;
import com.k404.Cookey.domain.user.entity.User;
import com.k404.Cookey.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDslRepository commentDslRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Comment> findByRecipeId(Long recipeId, int offset, int limit){

        return commentDslRepository.findByRecipeId(recipeId, offset, limit);
    }

    @Transactional
    public Long add(RequestCommentDto commentDto){

        Recipe recipe = recipeRepository.findById(commentDto.getRecipeId()).orElseThrow(()->new IllegalArgumentException("해당 아이디의 레시피가 없습니다. id : " + commentDto.getRecipeId()));
        User user = userRepository.findById(commentDto.getUserId()).orElseThrow(()->new IllegalArgumentException("해당 아이디의 유저가 없습니다. id : " + commentDto.getUserId()));

        Comment parentComment = null;
        if(commentDto.getPid() != null) parentComment = commentRepository.findById(commentDto.getPid()).orElseThrow(()->new IllegalArgumentException("해당 부모 아이디의 댓글이 없습니다. id : " + commentDto.getPid()));

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .imageUrl(commentDto.getImageUrl())
                .parent(parentComment)
                .user(user)
                .recipe(recipe)
                .build();

        return commentRepository.save(comment).getId();

    }

    @Transactional
    public Comment get(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 댓글이 없습니다. id : " + id));

        return comment;
    }

    @Transactional
    public Long update(Long id, UpdateCommentDto updateCommentDto){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 댓글이 없습니다. id : " + id));
        comment.update(updateCommentDto.getContent(), updateCommentDto.getImageUrl());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 댓글이 없습니다. id : " + id));
        commentRepository.delete(comment);
    }

    @Transactional
    public List<Comment> getByParentId(Long pid, int offset, int limit){
        List<Comment> comments = commentDslRepository.findByParentId(pid, offset, limit);
        return comments;
    }
}
