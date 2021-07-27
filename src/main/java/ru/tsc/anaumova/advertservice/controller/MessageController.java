package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Message;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @PostMapping
    @Operation(summary = "Отправить сообщение")
    public void addComment(@RequestBody Message message){
        //return redirect /messages
    }

    @PutMapping
    @Operation(summary = "Редактировать сообщение")
    public void updateComment(@RequestBody Message message){
        //return redirect /messages
    }

    @DeleteMapping("/{messageId}")
    @Operation(summary = "Удалить сообщение")
    public void deleteComment(@PathVariable String messageId){
        //return redirect /messages
    }

}
