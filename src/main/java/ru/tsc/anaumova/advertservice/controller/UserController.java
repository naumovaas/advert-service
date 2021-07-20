package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    @Operation(summary = "Просмотр списка пользователей")
    public void showUsers(){
        //return list
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр профиля пользователя")
    public void showUserDetails(@PathVariable String id){

    }

    @GetMapping("/{id}/sales-history")
    @Operation(summary = "Просмотр истории объявлений пользователя")
    public void showUserSalesHistory(@PathVariable String id){

    }

    @PostMapping("/add")
    @Operation(summary = "Добавить пользователя")
    public void addUser(@RequestBody User user){
        //return redirect /{id}
    }

    @PostMapping("/update")
    @Operation(summary = "Редактировать профиль пользователя")
    public void updateUser(@RequestBody User user){
        //return redirect /{id}
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить пользователя")
    public void deleteUser(@PathVariable String id){
        //return redirect /
    }

}
