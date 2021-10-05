package ru.tsc.anaumova.advertservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.tsc.anaumova.advertservice.model.Comment;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Schema(description = "Объявление")
public class AdvertDto {

    private Long advertId;

    @Schema(description = "Текст")
    private String text;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Дата и время добавления")
    private Timestamp date;

    @Schema(description = "Признак, что оплачено нахождение объявления в топе выдачи")
    private Boolean priorityFlag;

    @Schema(description = "Ид автора")
    private int userId;

    @Schema(description = "Ид категории")
    private int categoryId;

    @Schema(description = "Комментарии под объявлением")
    private List<Comment> comments;

}
