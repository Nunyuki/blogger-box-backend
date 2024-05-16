package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllByCategoryId(Category category);
    List<Post> getAll();
    Post  getById(UUID id);
    Post create(String title, String content, Category category);
    Post update(UUID id, String title, String content);
    void deleteById(UUID id);

    List<Post> findAllPostByTitleOrContent(String title, String content);
}
