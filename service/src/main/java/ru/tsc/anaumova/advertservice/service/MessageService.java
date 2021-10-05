package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.Message;
import ru.tsc.anaumova.advertservice.repository.MessageRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Page<Message> findByDialogId(Integer dialogId, Pageable pageable) {
        List<Message> messages = messageRepository.findByDialogId(dialogId, pageable)
                .stream()
                .collect(Collectors.toList());
        return new PageImpl<>(messages, pageable, messages.size());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void save(Message message) {
        message.setDate(new Timestamp(new Date().getTime()));
        messageRepository.save(message);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(Message message) throws EntityNotFoundException {
        final Message messageFromDb = messageRepository
                .findById(message.getMessageId())
                .orElseThrow(EntityNotFoundException::new);
        messageFromDb.setText(message.getText());
        messageRepository.save(messageFromDb);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void delete(Long messageId) {
        messageRepository.deleteById(messageId);
    }

}