package ru.tsc.anaumova.advertservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.DialogDto;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.Dialog;
import ru.tsc.anaumova.advertservice.service.DialogService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DialogServiceFacade {

    private final DialogService dialogService;

    private final MapperDto<Dialog, DialogDto> dialogMapperDto;

    @Autowired
    public DialogServiceFacade(DialogService dialogService) {
        this.dialogService = dialogService;
        this.dialogMapperDto = new MapperDto<>(DialogDto.class, Dialog.class);
    }

    public Page<DialogDto> findAll(Pageable pageable) {
        List<DialogDto> dialogues = dialogService.findAll(pageable)
                .stream()
                .map(dialogMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dialogues, pageable, dialogues.size());

    }

}
