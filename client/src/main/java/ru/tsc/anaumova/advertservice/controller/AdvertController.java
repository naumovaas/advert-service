package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.service.AdvertService;

@RestController
@RequestMapping("/categories/{categoryId}/adverts")
public class AdvertController {

    private final AdvertService advertService;

    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping
    @Operation(summary = "Просмотр списка объявлений")
    public ResponseEntity<Page<AdvertDto>> showAdverts(
            @PathVariable Integer categoryId,
            @RequestParam(name = "status", required = false) final String status,
            Pageable pageable
    ) {
        return new ResponseEntity<>(advertService.findAllByFilter(categoryId, status, pageable), HttpStatus.OK);
    }

    @GetMapping("/{advertId}")
    @Operation(summary = "Просмотр объявления")
    public ResponseEntity<AdvertDto> showAdvertDetails(
            @PathVariable Integer categoryId, @PathVariable Long advertId
    ) throws EntityNotFoundException {
        return new ResponseEntity<>(advertService.findById(advertId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить объявление")
    public void addAdvert(@PathVariable Integer categoryId, @RequestBody AdvertDto advertDto) {
        advertService.save(advertDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать объявление")
    public void updateAdvert(
            @PathVariable Integer categoryId, @RequestBody AdvertDto advertDto
    ) throws EntityNotFoundException {
        advertService.update(advertDto);
    }

    @DeleteMapping("/{advertId}")
    @Operation(summary = "Удалить объявление")
    public void deleteAdvert(@PathVariable Integer categoryId, @PathVariable Long advertId) {
        advertService.delete(advertId);
    }

    @PatchMapping
    @Operation(summary = "Установить флаг приоритета для объявления")
    public void updateAdvert(
            @PathVariable Integer categoryId, @RequestParam Long advertId
    ) throws EntityNotFoundException {
        advertService.setPriorityFlag(advertId);
    }

}
