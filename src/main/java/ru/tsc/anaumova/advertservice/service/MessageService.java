package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.MessageDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.Message;
import ru.tsc.anaumova.advertservice.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final Mapper<Message, MessageDto> messageMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = new Mapper<>(MessageDto.class, Message.class);
    }

    public Page<MessageDto> findByDialogId(Integer dialogId, Pageable pageable) {
        List<MessageDto> messages = messageRepository.findByDialogId(dialogId, pageable)
                .stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
                return new PageImpl<>(messages);
    }

    public void save(MessageDto messageDto) {
        final Message message = messageMapper.toEntity(messageDto);
        messageRepository.save(message);
    }

    public void update(MessageDto messageDto) throws EntityNotFoundException {
        final Message message = messageRepository
                .findById(messageDto.getMessageId())
                .orElseThrow(EntityNotFoundException::new);
        message.setText(messageDto.getText());
        messageRepository.save(message);
    }

    public void delete(Long messageId) {
        messageRepository.deleteById(messageId);
    }

}