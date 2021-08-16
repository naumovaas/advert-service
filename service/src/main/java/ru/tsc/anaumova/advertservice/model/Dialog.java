package ru.tsc.anaumova.advertservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "dialog")
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long dialogId;

    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User seller;

}