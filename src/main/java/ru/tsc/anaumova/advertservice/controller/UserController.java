package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;
import ru.tsc.anaumova.advertservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(summary = "Просмотр списка пользователей")
    public Iterable<User> showUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр профиля пользователя")
    public User showUserDetails(@PathVariable Long id) throws Exception {
        return userService.findById(id).orElseThrow(() -> new Exception("Ошибка. Не найден пользователь с ID - " + id));

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
