package ru.tsc.anaumova.advertservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.MessageDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.Message;
import ru.tsc.anaumova.advertservice.service.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceFacade {

    private final MessageService messageService;

    private final MapperDto<Message, MessageDto> messageMapperDto;

    @Autowired
    public MessageServiceFacade(MessageService messageService) {
        this.messageService = messageService;
        this.messageMapperDto = new MapperDto<>(MessageDto.class, Message.class);
    }

    public Page<MessageDto> findByDialogId(Integer dialogId, Pageable pageable) {
        List<MessageDto> messages = messageService.findByDialogId(dialogId, pageable)
                .stream()
                .map(messageMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(messages, pageable, messages.size());
    }

    public void save(MessageDto messageDto) {
        final Message message = messageMapperDto.toEntity(messageDto);
        messageService.save(message);
    }

    public void update(MessageDto messageDto, UserDetails userDetails) throws EntityNotFoundException {
        final Message message = messageMapperDto.toEntity(messageDto);
        messageService.update(message, userDetails);
    }

    public void delete(Long messageId) {
        messageService.delete(messageId);
    }

}
