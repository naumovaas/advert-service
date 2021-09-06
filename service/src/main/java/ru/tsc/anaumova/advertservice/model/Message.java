package ru.tsc.anaumova.advertservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long messageId;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "dialog_id")
    private Integer dialogId;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User author;

}
