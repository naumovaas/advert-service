package ru.tsc.anaumova.advertservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class MessageDto {

    private Long messageId;

    private int dialogId;

    private String text;

    private Timestamp date;

}
