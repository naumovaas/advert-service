package ru.tsc.anaumova.advertservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.MessageDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.facade.MessageServiceFacade;

@RestController
@RequestMapping("/dialogues/{dialogId}")
public class MessageController {

    private final MessageServiceFacade messageServiceFacade;

    @Autowired
    public MessageController(MessageServiceFacade messageServiceFacade) {
        this.messageServiceFacade = messageServiceFacade;
    }

    @GetMapping
    @Operation(summary = "Просмотр сообщений")
    public ResponseEntity<Page<MessageDto>> showList(@PathVariable Integer dialogId, Pageable pageable) {
        return new ResponseEntity<>(messageServiceFacade.findByDialogId(dialogId, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Отправить сообщение")
    @Parameter(name = "dialogId", description = "Ид диалога")
    public void add(@PathVariable String dialogId, @RequestBody MessageDto messageDto) {
        messageServiceFacade.save(messageDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать сообщение")
    @Parameter(name = "dialogId", description = "Ид диалога")
    public void update(@PathVariable String dialogId, @RequestBody MessageDto messageDto) throws EntityNotFoundException {
        messageServiceFacade.update(messageDto);
    }

    @DeleteMapping("/{messageId}")
    @Operation(summary = "Удалить сообщение")
    @Parameter(name = "dialogId", description = "Ид диалога")
    @Parameter(name = "messageId", description = "Ид удаляемого сообщения")
    public void delete(@PathVariable String dialogId, @PathVariable Long messageId) {
        messageServiceFacade.delete(messageId);
    }

}
