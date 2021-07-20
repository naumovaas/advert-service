package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Message;

@RestController
@RequestMapping("/messenger")
public class MessageController {

    @GetMapping("/")
    @Operation(summary = "Просмотр списка диалогов")
    public void showDialogList(){
        //return list
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр диалога")
    public void showMessages(@PathVariable String id){
        //return list
    }

    @PostMapping("/send-message")
    @Operation(summary = "Отправить сообщение")
    public void sendMessage(@RequestBody Message message){
        //return redirect /{id}
    }

}
