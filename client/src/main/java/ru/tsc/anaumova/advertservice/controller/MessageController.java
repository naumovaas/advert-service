package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.MessageDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.facade.MessageServiceFacade;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageServiceFacade messageServiceFacade;

    @Autowired
    public MessageController(MessageServiceFacade messageServiceFacade) {
        this.messageServiceFacade = messageServiceFacade;
    }

    @GetMapping
    @Operation(summary = "Просмотр сообщений")
    @Parameter(name = "dialogId", description = "Ид диалога")
    public ResponseEntity<Page<MessageDto>> showList(@RequestParam Integer dialogId, Pageable pageable) {
        return new ResponseEntity<>(messageServiceFacade.findByDialogId(dialogId, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Отправить сообщение")
    public void add(@RequestBody MessageDto messageDto) {
        messageServiceFacade.save(messageDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать сообщение")
    public void update(@RequestBody MessageDto messageDto, @AuthenticationPrincipal UserDetails userDetails) throws EntityNotFoundException {
        messageServiceFacade.update(messageDto, userDetails);
    }

    @DeleteMapping("/{messageId}")
    @Operation(summary = "Удалить сообщение")
    @Parameter(name = "messageId", description = "Ид удаляемого сообщения")
    public void delete(@PathVariable Long messageId) {
        messageServiceFacade.delete(messageId);
    }

}
