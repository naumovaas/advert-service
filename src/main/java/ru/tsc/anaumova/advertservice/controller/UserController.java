package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.service.AdvertService;
import ru.tsc.anaumova.advertservice.service.UserService;



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
    public Page<UserDto> showUsers(Pageable pageable){
        return userService.findAll(pageable);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Просмотр профиля пользователя")
    public UserDto showUserDetails(@PathVariable Long userId) throws EntityNotFoundException {
        return userService.findById(userId);
    }

    @GetMapping("/{userId}/sales-history")
    @Operation(summary = "Просмотр истории объявлений пользователя")
    public Page<AdvertDto> showUserSalesHistory(@PathVariable Integer userId, Pageable pageable) {
        return advertService.findByUserId(userId, pageable);
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
    public String deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
        return "redirect:/users";
    }

}
