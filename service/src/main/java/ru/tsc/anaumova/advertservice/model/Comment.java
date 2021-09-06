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

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "advert_id")
    private Integer advertId;

    @ManyToOne
    @JoinColumn(name = "advert_id", insertable = false, updatable = false)
    private Advert advert;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User author;

}
