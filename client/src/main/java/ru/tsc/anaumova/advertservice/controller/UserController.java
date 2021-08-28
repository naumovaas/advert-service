package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
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
    public ResponseEntity<Page<UserDto>> showUsers(Pageable pageable){
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Просмотр профиля пользователя")
    public ResponseEntity<String> showUserDetails(@PathVariable Long userId) throws EntityNotFoundException {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/sales-history")
    @Operation(summary = "Просмотр истории объявлений пользователя")
    public ResponseEntity<Page<AdvertDto>> showUserSalesHistory(@PathVariable Integer userId, Pageable pageable) {
        return new ResponseEntity<>(advertService.findByUserId(userId, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить пользователя")
    public String addUser(@RequestParam String userJson){
        userService.save(userJson);
        return "redirect:/users/";
    }

    @PutMapping
    @Operation(summary = "Редактировать профиль пользователя")
    public String updateUser(@RequestParam String userDtoJson) throws EntityNotFoundException {
        userService.update(userDtoJson);
        return "redirect:/users/";
    }

    //TODO обновление пароля отдельным контроллером

    @DeleteMapping("/{userId}")
    @Operation(summary = "Удалить пользователя")
    public String deleteUser(@PathVariable Long userId){
        userService.delete(userId);
        return "redirect:/users";
    }

}
