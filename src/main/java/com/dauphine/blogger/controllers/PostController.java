package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.exception.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import com.dauphine.blogger.services.impl.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private final PostService service;
    private final PostServiceImpl postServiceImpl;

    public PostController(PostService service, PostServiceImpl postServiceImpl){
        this.service = service;
        this.postServiceImpl = postServiceImpl;
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new post endpoint",
            description = "Create a new post '{title}' by request body"
    )
    public ResponseEntity<Post> createPost(
            @Parameter(description = "Title of the post")
            @RequestBody CreationPostRequest postRequest) throws CategoryNotFoundByIdException {
        Post post = service.create(postRequest);
        return ResponseEntity
                .created(URI.create("v1/posts/"+post.getId()))
                .body(post);
   }

    @PatchMapping("/{id}/")
    @Operation(
            summary = "Update post's endpoint",
            description = "Update by request body"
    )
    public ResponseEntity<Post> updatePost(@Parameter(description = "Give the new title of the post")
                         @RequestBody UpdatePostRequest postRequest) throws PostNotFoundByIdException{
        Post p =service.update(postRequest.getPostId(), postRequest.getTitle(), postRequest.getContent());
        return ResponseEntity.ok(p);    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete post endpoint",
            description = "Delete a post by '{id}'"
    )
    public ResponseEntity<?> deletePost(
            @Parameter(description = "Give the id of the post to delete")
            @PathVariable UUID id) throws PostNotFoundByIdException {
        service.getById(id);
        service.deleteById(id);
        return  ResponseEntity.ok().build();
    }

    @GetMapping()
    @Operation(
            summary = "Retrieve all posts ordered by created_date endpoint ",
            description = "Returns a list of all posts filter like name or content"
    )
    public ResponseEntity<List<Post>> retrieveAllPosts(@RequestParam(required = false)String value) {
        List<Post> posts = value == null || value.isBlank()
                ? service.getAll()
                : service.findAllPostByTitleOrContent(value,value);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/categoryId/{id}/{posts}")
    @Operation(
            summary = "Retrieve all posts ordered by category endpoint",
            description = "Returns a list of all posts"
    )
    public ResponseEntity<List<Post>> retrievePostsByCategory(
            @Parameter (description="Category id wanted")
            @PathVariable Category category
    ) throws CategoryNotFoundByIdException {
        List<Post> posts = service.getAllByCategoryId(category);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Post by id",
            description = "Post by ID given"
    )
    public ResponseEntity<Post> getPostById(
            @Parameter (description = "Id of the post")
            @PathVariable UUID id) throws PostNotFoundByIdException {
        Post post = service.getById(id);
        return ResponseEntity.ok(post);
    }
}
