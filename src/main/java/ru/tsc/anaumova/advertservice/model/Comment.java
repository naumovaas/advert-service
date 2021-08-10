package ru.tsc.anaumova.advertservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long commentId;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Timestamp date;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User author;
//
//    @ManyToOne
//    @JoinColumn(name = "advert_id")
//    private Advert advert;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "advert_id")
    private int advertId;

}
