package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.Advert;
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
    public Page<AdvertDto> showAdverts(
            @PathVariable Integer categoryId,
            @RequestParam(name = "filter", required = false) final String filter,
            Pageable pageable
    ){
        return advertService.findAllByFilter(filter, pageable);
    }

    @GetMapping("/{advertId}")
    @Operation(summary = "Просмотр объявления")
    public AdvertDto showAdvertDetails(@PathVariable Integer categoryId, @PathVariable Long advertId) throws EntityNotFoundException {
        return advertService.findById(advertId);

    }

    @PostMapping
    @Operation(summary = "Добавить объявление")
    public void addAdvert(@PathVariable Integer categoryId, @RequestBody Advert advert){
        //return redirect /{id}
    }

    @PutMapping
    @Operation(summary = "Редактировать объявление")
    public void updateAdvert(@PathVariable Integer categoryId, @RequestBody Advert advert){
        //return redirect /{id}
    }

    @DeleteMapping("/{advertId}")
    @Operation(summary = "Удалить объявление")
    public void deleteAdvert(@PathVariable Integer categoryId, @PathVariable String advertId){
        //return redirect /
    }

}
