package ru.tsc.anaumova.advertservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentDto {

    private Long commentId;

    private String text;

    private Timestamp date;

    //private User author;

}
