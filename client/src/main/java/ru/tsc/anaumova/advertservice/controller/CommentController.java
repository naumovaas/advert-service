package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.CommentDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.service.CommentService;

@RestController
@RequestMapping("adverts/{advertId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @Operation(summary = "Добавить комментарий")
    public void addComment(@PathVariable String advertId, @RequestBody CommentDto commentDto){
        commentService.save(commentDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать комментарий")
    public void updateComment(@PathVariable String advertId, @RequestBody CommentDto commentDto) throws EntityNotFoundException {
        commentService.update(commentDto);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удалить комментарий")
    public void deleteComment(@PathVariable String advertId, @PathVariable Long commentId){
        commentService.delete(commentId);
    }

}