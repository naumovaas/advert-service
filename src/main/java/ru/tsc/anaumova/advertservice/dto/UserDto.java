package ru.tsc.anaumova.advertservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long userId;

    private String username;

    private String firstName;

    private String lastName;

    private int rating;

}