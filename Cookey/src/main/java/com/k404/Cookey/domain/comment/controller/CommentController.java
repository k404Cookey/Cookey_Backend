package com.k404.Cookey.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import com.k404.Cookey.domain.comment.dto.RequestCommentDto;
import com.k404.Cookey.domain.comment.dto.ResponseCommentDto;
import com.k404.Cookey.domain.comment.dto.UpdateCommentDto;
import com.k404.Cookey.domain.comment.entity.Comment;
import com.k404.Cookey.domain.comment.service.CommentService;
import com.k404.Cookey.domain.common.Result;
import com.k404.Cookey.domain.common.ResultList;
import com.k404.Cookey.domain.recipe.dto.ResponseDto;
import com.k404.Cookey.domain.recipe.dto.UpdateRequestDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("")
    public ResultList<ResponseCommentDto> getByParentId(
            @RequestParam(required = true) Long pid,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "0") int offset){

        List<Comment> comments = commentService.getByParentId(pid,offset, limit);
        return new ResultList<ResponseCommentDto>(comments.stream().map(c->new ResponseCommentDto(c)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Result<ResponseCommentDto> get(@PathVariable Long id){
        Comment comment = commentService.get(id);
        return new Result<ResponseCommentDto>(new ResponseCommentDto(comment));
    }

    @PutMapping("/{id}")
    public Result<Long> update (@PathVariable Long id, @RequestBody @Validated UpdateCommentDto commentDto){
        Long updateId = commentService.update(id, commentDto);
        return new Result<Long>(updateId);
    }

    @PostMapping("")
    public Result<ResponseCommentDto> add(@RequestBody @Validated RequestCommentDto commentDto){

        Long id = commentService.add(commentDto);
        Comment comment = commentService.get(id);
        return new Result<ResponseCommentDto>(new ResponseCommentDto(comment));

    }
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id){
        commentService.delete(id);
        return new Result<Boolean>(true);
    }


}
