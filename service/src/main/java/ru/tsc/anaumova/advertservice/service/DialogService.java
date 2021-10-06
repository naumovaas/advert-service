package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.model.Dialog;
import ru.tsc.anaumova.advertservice.repository.DialogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DialogService {

    private final DialogRepository dialogRepository;

    @Autowired
    public DialogService(DialogRepository dialogRepository) {
        this.dialogRepository = dialogRepository;
    }

    public Page<Dialog> findAll(Pageable pageable) {
        List<Dialog> dialogues = dialogRepository.findAll(pageable)
                .stream()
                .collect(Collectors.toList());
        return new PageImpl<>(dialogues, pageable, dialogues.size());

    }

}