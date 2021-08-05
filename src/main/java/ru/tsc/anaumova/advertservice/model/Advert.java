package ru.tsc.anaumova.advertservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "advert")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long advertId;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "priority_flag")
    private boolean priorityFlag;

    @Column(name = "user_id")
    private int userId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
