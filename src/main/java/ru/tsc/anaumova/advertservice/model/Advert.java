package ru.tsc.anaumova.advertservice.model;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Long advertId) {
        this.advertId = advertId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isPriorityFlag() {
        return priorityFlag;
    }

    public void setPriorityFlag(boolean priorityFlag) {
        this.priorityFlag = priorityFlag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
