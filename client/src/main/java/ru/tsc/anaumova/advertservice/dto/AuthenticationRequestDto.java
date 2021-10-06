package ru.tsc.anaumova.advertservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Данные для аутентификации пользователя")
public class AuthenticationRequestDto {

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Пароль")
    private String password;

}