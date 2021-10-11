package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.dto.UserRegistryDto;
import ru.tsc.anaumova.advertservice.exception.AccessDeniedException;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.ExistUsernameException;
import ru.tsc.anaumova.advertservice.exception.IncorrectPasswordException;
import ru.tsc.anaumova.advertservice.facade.UserServiceFacade;
import ru.tsc.anaumova.advertservice.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceFacade userServiceFacade;

    @Autowired
    public UserController(final UserServiceFacade userServiceFacade) {
        this.userServiceFacade = userServiceFacade;
    }

    @GetMapping
    @Operation(summary = "Просмотр списка пользователей")
    public ResponseEntity<Page<UserDto>> showList(Pageable pageable) {
        return new ResponseEntity<>(userServiceFacade.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Просмотр профиля пользователя")
    @Parameter(name = "userId", description = "Ид пользователя")
    public ResponseEntity<UserDto> showUserDetails(@PathVariable Long userId) throws EntityNotFoundException {
        return new ResponseEntity<>(userServiceFacade.findById(userId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить пользователя")
    public void add(@RequestBody UserRegistryDto userRegistryDto) throws ExistUsernameException {
        userServiceFacade.save(userRegistryDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать профиль пользователя")
    public void update(@RequestBody UserDto userDto, @RequestParam Long userId, @AuthenticationPrincipal UserDetails userDetails)
            throws EntityNotFoundException, ExistUsernameException, AccessDeniedException {
        userServiceFacade.update(userDto, userId, userDetails);
    }

    @PatchMapping
    @Operation(summary = "Изменить пароль пользователя")
    @Parameter(name = "userId", description = "Ид пользователя")
    @Parameter(name = "oldPassword", description = "Старый пароль")
    @Parameter(name = "newPassword", description = "Новый пароль")
    public void updatePassword(
            @RequestParam Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IncorrectPasswordException, EntityNotFoundException, AccessDeniedException {
        userServiceFacade.updatePassword(userId, oldPassword, newPassword, userDetails);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Удалить пользователя")
    @Parameter(name = "userId", description = "Ид удаляемого пользователя")
    public void delete(@PathVariable Long userId) {
        userServiceFacade.delete(userId);
    }

}
