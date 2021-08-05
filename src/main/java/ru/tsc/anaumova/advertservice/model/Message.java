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

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

}
