package com.dauphine.blogger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String title;

    public Category(){

    }
    public Category(UUID id) {
        this.id = id;
    }
    public Category(UUID id, String title) {
        this.id = id;
        this.title=title;
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
}
