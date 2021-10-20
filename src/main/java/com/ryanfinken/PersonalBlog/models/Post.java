package com.ryanfinken.PersonalBlog.models;

import javax.persistence.Entity;

@Entity
public class Post extends AbstractEntity {
    private String title;

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
}
