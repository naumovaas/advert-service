package ru.tsc.anaumova.advertservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.tsc.anaumova.advertservice.model.Comment;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class AdvertDto {

    private Long advertId;

    private String text;

    private String status;

    private Timestamp date;

    private Boolean priorityFlag;

    private int userId;

    private int categoryId;

    private List<Comment> comments;

}
