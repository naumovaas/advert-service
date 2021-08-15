package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.MessageDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.service.MessageService;

@RestController
@RequestMapping("/dialogues/{dialogId}")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @Operation(summary = "Просмотр сообщений")
    public Page<MessageDto> showMessages(@PathVariable Integer dialogId, Pageable pageable){
        return messageService.findByDialogId(dialogId, pageable);
    }

    @PostMapping
    @Operation(summary = "Отправить сообщение")
    public String addComment(@PathVariable String dialogId, @RequestBody MessageDto messageDto){
        messageService.save(messageDto);
        return "redirect:/dialogues/" + dialogId;
    }

    @PutMapping
    @Operation(summary = "Редактировать сообщение")
    public String updateComment(@PathVariable String dialogId, @RequestBody MessageDto messageDto) throws EntityNotFoundException {
        messageService.update(messageDto);
        return "redirect:/dialogues/" + dialogId;
    }

    @DeleteMapping("/{messageId}")
    @Operation(summary = "Удалить сообщение")
    public String deleteComment(@PathVariable String dialogId, @PathVariable Long messageId){
        messageService.delete(messageId);
        return "redirect:/dialogues/" + dialogId;
    }

}
