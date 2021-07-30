package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.service.AdvertService;
import ru.tsc.anaumova.advertservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final AdvertService advertService;

    @Autowired
    public UserController(final UserService userService, final AdvertService advertService) {
        this.userService = userService;
        this.advertService = advertService;
    }

    @GetMapping
    @Operation(summary = "Просмотр списка пользователей")
    //TODO добавить фильтр
    public Iterable<User> showUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Просмотр профиля пользователя")
    public User showUserDetails(@PathVariable Long userId) throws EntityNotFoundException {
        return userService.findById(userId);
    }

    @GetMapping("/{userId}/sales-history")
    @Operation(summary = "Просмотр истории объявлений пользователя")
    public Iterable<Advert> showUserSalesHistory(@PathVariable Integer userId) {
        return advertService.findByUserId(userId);
    }

    @PostMapping
    @Operation(summary = "Добавить пользователя")
    public void addUser(@RequestBody User user){
        //return redirect /{id}
    }

    @PutMapping
    @Operation(summary = "Редактировать профиль пользователя")
    public void updateUser(@RequestBody User user){
        //return redirect /{id}
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Удалить пользователя")
    public void deleteUser(@PathVariable String userId){
        //return redirect /
    }

}
