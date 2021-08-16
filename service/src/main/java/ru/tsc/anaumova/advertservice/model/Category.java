package ru.tsc.anaumova.advertservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long categoryId;

    @Column(name = "title")
    private String title;

//    @ManyToOne
//    @JoinColumn(name = "parent_category_id")
//    private Category parentCategory;

    @Column(name = "parent_category_id")
    private int parentCategoryId;

}
