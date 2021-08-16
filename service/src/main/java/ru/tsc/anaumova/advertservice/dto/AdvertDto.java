package ru.tsc.anaumova.advertservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AdvertDto {

    private Long advertId;

    private String text;

    private String status;

    private Timestamp date;

    private boolean priorityFlag;

    private int userId;

    private int categoryId;

}
