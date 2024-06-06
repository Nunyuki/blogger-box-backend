package com.dauphine.blogger.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Post(){

    }
    public Post(UUID id, String title, String content, Category category, Date createdDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
    }

    public Post(String title, String content){
        this.title = title;
        this.content = content;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date newDate) {
        this.createdDate = newDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
