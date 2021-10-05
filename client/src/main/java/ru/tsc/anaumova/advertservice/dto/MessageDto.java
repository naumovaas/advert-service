package ru.tsc.anaumova.advertservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Schema(description = "Сообщение")
public class MessageDto {

    private Long messageId;

    @Schema(description = "Ид диалога, в рамках которого создано")
    private Integer dialogId;

    @Schema(description = "Ид автора")
    private Integer userId;

    @Schema(description = "Текст")
    private String text;

    @Schema(description = "Дата и время добавления")
    private Timestamp date;

}
