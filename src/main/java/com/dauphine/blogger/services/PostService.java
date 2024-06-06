package com.dauphine.blogger.services;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.exception.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllByCategoryId(Category category) throws CategoryNotFoundByIdException;
    List<Post> getAll();
    Post  getById(UUID id) throws PostNotFoundByIdException;
    Post create(CreationPostRequest postRequest) throws CategoryNotFoundByIdException;
    Post update(UUID id, String title, String content) throws PostNotFoundByIdException;
    boolean deleteById(UUID id) throws PostNotFoundByIdException;
    List<Post> findAllPostByTitleOrContent(String title, String content);
}
