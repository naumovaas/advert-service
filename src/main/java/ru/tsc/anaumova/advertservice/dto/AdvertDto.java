package ru.tsc.anaumova.advertservice.dto;

import ru.tsc.anaumova.advertservice.model.Category;

import javax.persistence.*;
import java.sql.Timestamp;

public class AdvertDto {

    private Long advertId;

    private String text;

    private String status;

    private Timestamp date;

    private boolean priorityFlag;

    private int userId;

    private Category category;

}
