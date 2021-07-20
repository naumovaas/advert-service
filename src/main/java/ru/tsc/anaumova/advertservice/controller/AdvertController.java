package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Advert;

@RestController
@RequestMapping("/advert")
public class AdvertController {

    @GetMapping("/")
    @Operation(summary = "Просмотр списка объявлений")
    public void showAdverts(){
        //return list
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр объявления")
    public void showAdvertDetails(@PathVariable String id){

    }

    @PostMapping("/filter")
    @Operation(summary = "Поиск объявлений по заданному фмльтру")
    public void showAdvertsFiltered(@RequestBody String searchString){

    }

    @PostMapping("/add")
    @Operation(summary = "Добавить объявление")
    public void addAdvert(@RequestBody Advert advert){
        //return redirect /{id}
    }

    @PostMapping("/update")
    @Operation(summary = "Редактировать объявление")
    public void updateAdvert(@RequestBody Advert advert){
        //return redirect /{id}
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить объявление")
    public void deleteAdvert(@PathVariable String id){
        //return redirect /
    }

}
