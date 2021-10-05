package ru.tsc.anaumova.advertservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Категория")
public class CategoryDto {

    private Long categoryId;

    @Schema(description = "Наименование")
    private String title;

    @Schema(description = "Ид родительской категории")
    private Integer parentCategoryId;

}
