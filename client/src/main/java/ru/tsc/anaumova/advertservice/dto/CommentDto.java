package ru.tsc.anaumova.advertservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Schema(description = "Комментарий под объявлением")
public class CommentDto {

    private Long commentId;

    @Schema(description = "Текст")
    private String text;

    @Schema(description = "Дата и время добавления")
    private Timestamp date;

    @Schema(description = "Ид объявления, к которому относится")
    private Integer advertId;

    @Schema(description = "Ид автора")
    private Integer userId;

}
