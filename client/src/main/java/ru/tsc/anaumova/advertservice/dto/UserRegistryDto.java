package ru.tsc.anaumova.advertservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Пользователь с полным списком полей")
public class UserRegistryDto {

    @Schema(description = "Уникальное имя пользователя")
    private String username;

    @Schema(description = "Пароль")
    private String password;

    @Schema(description = "Имя")
    private String firstName;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Рейтинг")
    private Integer rating;

}
