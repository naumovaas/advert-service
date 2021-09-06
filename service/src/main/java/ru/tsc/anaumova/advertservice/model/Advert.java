package ru.tsc.anaumova.advertservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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
    private Boolean priorityFlag;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "category_id")
    private Integer categoryId;

    @OneToMany(mappedBy = "advert")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

}
