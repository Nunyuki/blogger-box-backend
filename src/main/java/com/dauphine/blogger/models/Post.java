package com.dauphine.blogger.models;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class Post {
    private UUID id;
    private String title;
    private String content;
    private Timestamp created_date;
    private UUID categoryId;

    public Post(UUID id, String title, String content, UUID categoryId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        setCreated_date();
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date() {
        this.created_date=new Timestamp(Instant.now().toEpochMilli());

    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
