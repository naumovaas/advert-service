package ru.tsc.anaumova.advertservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long categoryId;

    private String title;

    private int parentCategoryId;

}
