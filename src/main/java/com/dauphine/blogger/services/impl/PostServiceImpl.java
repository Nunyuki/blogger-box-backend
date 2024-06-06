package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.exception.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private CategoryService categoryService;

    public PostServiceImpl(PostRepository postRepository, CategoryService categoryService){
        this.postRepository = postRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Post> getAllByCategoryId(Category category) throws CategoryNotFoundByIdException {
        categoryService.getById(category.getId());
        return postRepository.findAllByCategory(category);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(UUID id) throws PostNotFoundByIdException {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(CreationPostRequest postRequest) throws CategoryNotFoundByIdException {
        Post p = new Post(UUID.randomUUID(), postRequest.getTitle(),postRequest.getContent(),
                categoryService.getById(postRequest.getCategoryId()),new Date());
        return postRepository.save(p);
    }

    @Override
    public Post update(UUID id, String title, String content) throws PostNotFoundByIdException {
        Post post = getById(id);
        if(post == null){
            return null;
        }

        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    @Override
    public boolean deleteById(UUID id) throws PostNotFoundByIdException {
        getById(id);
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Post> findAllPostByTitleOrContent(String title, String content) {
        return postRepository.findAllPostByTitleOrContent(title,content);
    }
}
