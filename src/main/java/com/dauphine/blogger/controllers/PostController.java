package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
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

        return service.createPost(postRequest.getTitle(),postRequest.getContent(),postRequest.getCategoryId());
   }

    @PatchMapping("/{id}/")
    @Operation(
            summary = "Update post's endpoint",
            description = "Update by request body"
    )
    public Post update(@Parameter(description = "Give the new title of the post")
                             @PathVariable UUID id,
                         @RequestBody UpdatePostRequest postRequest){
        //TODO
        return service.update(id,postRequest.getTitle(),postRequest.getContent());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete post endpoint",
            description = "Delete a post by '{id}'"
    )
    public void delete(
            @Parameter(description = "Give the id of the post to delete")
            @PathVariable UUID id){
        service.delete(id);
    }

    @GetMapping("/")
    @Operation(
            summary = "Retrieve all posts ordered by created_date endpoint",
            description = "Returns a list of all posts"
    )
    public List<Post> retrieveAllPostByDate(@RequestParam("date") String date) {
        return service.retrieveAllPostByDate();
    }

    @GetMapping("/{categoryId}/posts")
    @Operation(
            summary = "Retrieve all posts ordered by date endpoint",
            description = "Returns a list of all posts"
    )
    public List<Post> retrieveAllPostByCategory(
            @Parameter (description="Category id wanted")
            @PathVariable UUID categoryId
    ) {
        return service.retrieveAllPostByCategory(categoryId);
    }
}
