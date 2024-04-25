package com.dauphine.blogger.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="post")
public class Post {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="created_date")
    private Timestamp created_date;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Post(){

    }
    public Post(UUID id, String title, String content, Category category){
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategoryId(Category categoryId) {
        this.category = categoryId;
    }
}
