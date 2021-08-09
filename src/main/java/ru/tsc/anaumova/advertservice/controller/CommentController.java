package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Comment;

@RestController
@RequestMapping("adverts/{advertId}/comments")
public class CommentController {

    @PostMapping
    @Operation(summary = "Добавить комментарий")
    public void addComment(@RequestBody String text){
        //return redirect /{id}
    }

    @PutMapping
    @Operation(summary = "Редактировать комментарий")
    public void updateComment(@RequestBody Comment comment){
        //return redirect /advert/{id}
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удалить комментарий")
    public void deleteComment(@PathVariable String advertId, @PathVariable String commentId){
        //return redirect /advert/{id}
    }

}
