package ru.tsc.anaumova.advertservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Пользователь")
public class UserDto {

    private Long userId;

    @Schema(description = "имя пользователя")
    private String username;

    @Schema(description = "Имя")
    private String firstName;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Рейтинг")
    private Integer rating;

}