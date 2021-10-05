package ru.tsc.anaumova.advertservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.CommentDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.facade.CommentServiceFacade;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceFacade commentServiceFacade;

    @Autowired
    public CommentController(CommentServiceFacade commentServiceFacade) {
        this.commentServiceFacade = commentServiceFacade;
    }

    @PostMapping
    @Operation(summary = "Добавить комментарий")
    @Parameter(name = "advertId", description = "Ид объявления")
    public void add(@RequestBody CommentDto commentDto) {
        commentServiceFacade.save(commentDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать комментарий")
    @Parameter(name = "advertId", description = "Ид объявления")
    public void update(@RequestBody CommentDto commentDto) throws EntityNotFoundException {
        commentServiceFacade.update(commentDto);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Удалить комментарий")
    @Parameter(name = "advertId", description = "Ид объявления")
    @Parameter(name = "commentId", description = "Ид удаляемого комментария")
    public void delete(@PathVariable Long commentId) {
        commentServiceFacade.delete(commentId);
    }

}