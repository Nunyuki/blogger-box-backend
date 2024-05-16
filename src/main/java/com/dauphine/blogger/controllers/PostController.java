package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service){

        this.service = service;
    }

    @PostMapping("/")
    @Operation(
            summary = "Create a new post endpoint",
            description = "Create a new post '{title}' by request body"
    )
    public Post createPost(
            @Parameter(description = "Title of the post")
            @RequestBody CreationPostRequest postRequest) {

        return service.create(postRequest.getTitle(),postRequest.getContent(),postRequest.getCategory());
   }

    @PatchMapping("/{id}/")
    @Operation(
            summary = "Update post's endpoint",
            description = "Update by request body"
    )
    public Post updatePost(@Parameter(description = "Give the new title of the post")
                             @PathVariable UUID id,
                         @RequestBody UpdatePostRequest postRequest){
        return service.update(id,postRequest.getTitle(),postRequest.getContent());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete post endpoint",
            description = "Delete a post by '{id}'"
    )
    public void deletePost(
            @Parameter(description = "Give the id of the post to delete")
            @PathVariable UUID id){
        service.deleteById(id);
    }

    @GetMapping()
    @Operation(
            summary = "Retrieve all posts ordered by created_date endpoint ",
            description = "Returns a list of all posts filter like name or content"
    )
    public List<Post> retrieveAllPosts(@RequestParam(required = false)String value) {
        List<Post> posts = value == null || value.isBlank()
                ? service.getAll()
                : service.findAllPostByTitleOrContent(value,value);
        return posts;
    }

    @GetMapping("/{categoryId}/{id}")
    @Operation(
            summary = "Retrieve all posts ordered by date endpoint",
            description = "Returns a list of all posts"
    )
    public List<Post> retrievePostsByCategory(
            @Parameter (description="Category id wanted")
            @PathVariable Category category
    ) {
        return service.getAllByCategoryId(category);
    }
}
