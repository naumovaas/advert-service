package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.DialogDto;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.Dialog;
import ru.tsc.anaumova.advertservice.repository.DialogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DialogService {

    private final DialogRepository dialogRepository;

    private final MapperDto<Dialog, DialogDto> dialogMapperDto;

    @Autowired
    public DialogService(DialogRepository dialogRepository) {
        this.dialogRepository = dialogRepository;
        this.dialogMapperDto = new MapperDto<>(DialogDto.class, Dialog.class);
    }

    public Page<DialogDto> findAll(Pageable pageable) {
        List<DialogDto> dialogues = dialogRepository.findAll(pageable)
                .stream()
                .map(dialogMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dialogues);

    }

}