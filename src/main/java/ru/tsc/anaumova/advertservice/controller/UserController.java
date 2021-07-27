package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.exception.UserNotFoundException;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;
import ru.tsc.anaumova.advertservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Просмотр списка пользователей")
    public Iterable<User> showUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Просмотр профиля пользователя")
    public User showUserDetails(@PathVariable Long userId) throws UserNotFoundException {
        return userService.findById(userId);
    }

    @GetMapping("/{userId}/sales-history")
    @Operation(summary = "Просмотр истории объявлений пользователя")
    public void showUserSalesHistory(@PathVariable String userId){

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
