package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> retrieveAllPostByCategory(Category category);
    List<Post> retrieveAllPostByDate();
    Post  retrievePost(UUID id);
    Post createPost(String title, String content, Category category);
    Post update(UUID id, String title, String content);
    void delete(UUID id);

    List<Post> findAllPostByTitleOrContent(String title, String content);
}
