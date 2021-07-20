package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Comment;

@RestController
@RequestMapping("/advert/{id}")
public class CommentController {

    @PostMapping("/add-comment")
    @Operation(summary = "Добавить комментарий")
    public void addComment(@RequestBody Comment comment){
        //return redirect /{id}
    }

    @PostMapping("/update-comment")
    @Operation(summary = "Редактировать комментарий")
    public void updateComment(@RequestBody Comment comment){
        //return redirect /advert/{id}
    }

    @DeleteMapping("/delete-comment/{id}")
    @Operation(summary = "Удалить комментарий")
    public void deleteComment(@PathVariable String id){
        //return redirect /advert/{id}
    }

}
