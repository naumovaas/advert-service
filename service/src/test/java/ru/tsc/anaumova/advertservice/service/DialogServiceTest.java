package ru.tsc.anaumova.advertservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.tsc.anaumova.advertservice.dto.DialogDto;
import ru.tsc.anaumova.advertservice.model.Dialog;
import ru.tsc.anaumova.advertservice.repository.DialogRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DialogServiceTest {

    private final List<Dialog> dialogs = new ArrayList<>();

    private DialogService dialogService;

    @Before
    public void init() {
        initTestData();
        DialogRepository dialogRepository = mock(DialogRepository.class);
        when(dialogRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(dialogs));

        dialogService = new DialogService(dialogRepository);
    }

    private void initTestData() {
        dialogs.add(new Dialog());
        dialogs.add(new Dialog());
        dialogs.add(new Dialog());
    }

    @Test
    public void findAllTest() {
        Page<DialogDto> resultFindAll = dialogService.findAll(PageRequest.of(1, 2));
        Assert.assertNotNull(resultFindAll);
        Assert.assertNotNull(resultFindAll.getContent());
        Assert.assertEquals(3, resultFindAll.getContent().size());
    }

}
