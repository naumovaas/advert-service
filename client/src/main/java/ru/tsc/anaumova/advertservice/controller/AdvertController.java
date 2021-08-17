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
    ){
        return new ResponseEntity<>(advertService.findAllByFilter(categoryId, status, pageable), HttpStatus.OK);
    }

    @GetMapping("/{advertId}")
    @Operation(summary = "Просмотр объявления")
    public ResponseEntity<String> showAdvertDetails(@PathVariable Integer categoryId, @PathVariable Long advertId) throws EntityNotFoundException {
        return new ResponseEntity<>(advertService.findById(advertId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить объявление")
    public String addAdvert(@PathVariable Integer categoryId, @RequestParam String advertJson){
        advertService.save(advertJson);
        return "redirect:/categories/" + categoryId + "/adverts";
    }

    @PutMapping
    @Operation(summary = "Редактировать объявление")
    public String updateAdvert(@PathVariable Integer categoryId, @RequestParam String advertJson) throws EntityNotFoundException {
        advertService.update(advertJson);
        return "redirect:/categories/" + categoryId + "/adverts";
    }

    @DeleteMapping("/{advertId}")
    @Operation(summary = "Удалить объявление")
    public String deleteAdvert(@PathVariable Integer categoryId, @PathVariable Long advertId){
        advertService.delete(advertId);
        return "redirect:/categories/" + categoryId + "/adverts";
    }

}
