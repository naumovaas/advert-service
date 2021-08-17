package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.DialogDto;
import ru.tsc.anaumova.advertservice.service.DialogService;

@RestController
@RequestMapping("/dialogues")
public class DialogController {

    private final DialogService dialogService;

    @Autowired
    public DialogController(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    @GetMapping
    @Operation(summary = "Просмотр списка диалогов")
    public ResponseEntity<Page<DialogDto>> showDialogList(Pageable pageable){
        return new ResponseEntity<>(dialogService.findAll(pageable), HttpStatus.OK);
    }

}
