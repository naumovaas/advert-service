package ru.tsc.anaumova.advertservice.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

public class Message {

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

}
