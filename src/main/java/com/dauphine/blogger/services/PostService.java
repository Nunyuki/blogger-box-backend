package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> retrieveAllPostByCategory(UUID categoryId);
    List<Post> retrieveAllPostByDate();
    Post  retrievePost(UUID id);
    Post createPost(String title, String content, UUID categoryId);
    Post update(UUID id, String title, String content);
    void delete(UUID id);
}
