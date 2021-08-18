package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public String addComment(@PathVariable String advertId, @RequestParam String commentDtoJson){
        commentService.save(commentDtoJson);
        return "redirect:/adverts/" + advertId + "/comments";
    }

    @PutMapping
    @Operation(summary = "Редактировать комментарий")
    public String updateComment(@PathVariable String advertId, @RequestParam String commentDtoJson) throws EntityNotFoundException {
        commentService.update(commentDtoJson);
        return "redirect:/adverts/" + advertId + "/comments";
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удалить комментарий")
    public String deleteComment(@PathVariable String advertId, @PathVariable Long commentId){
        commentService.delete(commentId);
        return "redirect:/adverts/" + advertId + "/comments";
    }

}