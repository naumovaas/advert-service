package ru.tsc.anaumova.advertservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.tsc.anaumova.advertservice.model.Category;

@Getter
@Setter
public class CategoryDto {

    private Long categoryId;

    private String title;

    private Category parentCategory;

}
