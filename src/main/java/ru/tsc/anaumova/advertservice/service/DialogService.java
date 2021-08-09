package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.DialogDto;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.Dialog;
import ru.tsc.anaumova.advertservice.repository.DialogRepository;

@Service
public class DialogService {

    private final DialogRepository dialogRepository;

    private final Mapper<Dialog, DialogDto> dialogMapper;

    @Autowired
    public DialogService(DialogRepository dialogRepository) {
        this.dialogRepository = dialogRepository;
        this.dialogMapper = new Mapper<>(DialogDto.class, Dialog.class);
    }

}
