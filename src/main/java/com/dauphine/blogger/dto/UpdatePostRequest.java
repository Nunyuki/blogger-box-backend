package com.dauphine.blogger.dto;

import java.util.UUID;

public class UpdatePostRequest {

    private String title;
    private String content;
    private UUID postId;

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

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID categoryId) {
        this.postId = categoryId;
    }
}
