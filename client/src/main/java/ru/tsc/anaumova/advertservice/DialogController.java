package ru.tsc.anaumova.advertservice;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.DialogDto;
import ru.tsc.anaumova.advertservice.facade.DialogServiceFacade;

@RestController
@RequestMapping("/dialogues")
public class DialogController {

    private final DialogServiceFacade dialogServiceFacade;

    @Autowired
    public DialogController(DialogServiceFacade dialogServiceFacade) {
        this.dialogServiceFacade = dialogServiceFacade;
    }

    @GetMapping
    @Operation(summary = "Просмотр списка диалогов")
    public ResponseEntity<Page<DialogDto>> showList(Pageable pageable) {
        return new ResponseEntity<>(dialogServiceFacade.findAll(pageable), HttpStatus.OK);
    }

}
