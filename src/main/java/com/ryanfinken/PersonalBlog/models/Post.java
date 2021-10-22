package com.ryanfinken.PersonalBlog.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Post extends AbstractEntity {
    @CreationTimestamp
    Timestamp timestamp;

    @Column
    private String title;

    @Column(length = 100000)
    private String body;

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
