package ru.tsc.anaumova.advertservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.tsc.anaumova.advertservice.model.Message;
import ru.tsc.anaumova.advertservice.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    private final List<Message> messages = new ArrayList<>();

    private MessageService messageService;

    @Before
    public void init() {
        initTestData();
        MessageRepository messageRepository = mock(MessageRepository.class);
        when(messageRepository.findByDialogId(any(Integer.class), any(Pageable.class))).thenReturn(new PageImpl<>(messages));

        messageService = new MessageService(messageRepository);
    }

    private void initTestData() {
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());
    }

    @Test
    public void findByDialogIdTest() {
        Page<Message> resultFindByDialogId = messageService.findByDialogId(1, PageRequest.of(1, 2));
        Assert.assertNotNull(resultFindByDialogId);
        Assert.assertNotNull(resultFindByDialogId.getContent());
        Assert.assertEquals(3, resultFindByDialogId.getContent().size());
    }

}
