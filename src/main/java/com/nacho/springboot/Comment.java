package com.nacho.springboot;

import java.util.Date;

public class Comment {

    private final long id;
    private final String comment;
    private final Date date;

    public Comment(long id, String comment, Date date) {
        this.id = id;
        this.comment = comment;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
