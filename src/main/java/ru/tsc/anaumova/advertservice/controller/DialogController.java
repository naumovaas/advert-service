package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Message;

@RestController
@RequestMapping("/dialogues")
public class DialogController {

    @GetMapping
    @Operation(summary = "Просмотр списка диалогов")
    public void showDialogList(){
        //return list
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр диалога")
    public void showMessages(@PathVariable String id){
        //return list
    }

}
