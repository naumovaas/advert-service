package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Advert;

@RestController
@RequestMapping("/adverts")
public class AdvertController {

    @GetMapping
    @Operation(summary = "Просмотр списка объявлений")
    public void showAdverts(@RequestParam(name = "filter", required = false) final String filter){
        //return list
    }

    @GetMapping("/{advertId}")
    @Operation(summary = "Просмотр объявления")
    public void showAdvertDetails(@PathVariable String advertId){

    }

    @PostMapping
    @Operation(summary = "Добавить объявление")
    public void addAdvert(@RequestBody Advert advert){
        //return redirect /{id}
    }

    @PutMapping
    @Operation(summary = "Редактировать объявление")
    public void updateAdvert(@RequestBody Advert advert){
        //return redirect /{id}
    }

    @DeleteMapping("/{advertId}")
    @Operation(summary = "Удалить объявление")
    public void deleteAdvert(@PathVariable String advertId){
        //return redirect /
    }

}
