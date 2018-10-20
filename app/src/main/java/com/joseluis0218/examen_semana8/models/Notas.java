package com.joseluis0218.examen_semana8.models;

import com.orm.dsl.Table;

import java.util.Date;
@Table
public class Notas {
    private Long id;
    private String title;
    private String content;
    private Date date;
    private Boolean state;

    public Notas() {
    }

    public Notas(String title, String content, Date date, Boolean state) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}

