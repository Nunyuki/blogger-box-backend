package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    List<Post> temporaryPosts = new ArrayList<>();

    @PostMapping("/")
    @Operation(
            summary = "Create a new post endpoint",
            description = "Create a new post '{title}' by request body"
    )
    public String create(
            @Parameter(description = "Title of the post")
            @RequestBody CreationPostRequest postRequest) {
            Post post = new Post();

        //TODO
        return "Create new post with \nid: '%s'\ntitle: '%s'\ncategory_id: '%s'".formatted(post.getId(),post.getTitle(),post.getCategory_id());
    }

    @PatchMapping("/{id}/title")
    @Operation(
            summary = "Update post's title endpoint",
            description = "Update '{title}' by request body"
    )
    public String updateTitle(@Parameter(description = "Give the new title of the post")
                             @PathVariable UUID id,
                         @RequestBody UpdatePostRequest postRequest){
        //TODO
        return "Update post '%s' with title '%s'".formatted(id, postRequest.getTitle());
    }

    @PatchMapping("/{id}/content")
    @Operation(
            summary = "Update post's content endpoint",
            description = "Update '{content}' by request body"
    )
    public String updateContent(@Parameter(description = "Give the new content of the post")
                         @PathVariable UUID id,
                         @RequestBody UpdatePostRequest postRequest){
        //TODO
        return "Update post '%s' with content '%s'".formatted(id, postRequest.getContent());
    }

    @PatchMapping("/{id}/category_id")
    @Operation(
            summary = "Update post's category id endpoint",
            description = "Update '{category_id}' by request body"
    )
    public String updateCategoryId(@Parameter(description = "Give the new category id of the post")
                                @PathVariable UUID id,
                                @RequestBody UpdatePostRequest postRequest){
        //TODO
        return "Update post '%s' with category_id '%s'".formatted(id, postRequest.getCategory_id());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete post endpoint",
            description = "Delete a post by '{id}'"
    )
    public String delete(
            @Parameter(description = "Give the id of the post to delete")
            @PathVariable UUID id){
        // TODO
        return "Delete post '%s' with the title".formatted(id,"");
    }

    @GetMapping("/")
    @Operation(
            summary = "Retrieve all posts ordered by created_date endpoint",
            description = "Returns a list of all posts"
    )
    public List<Post> retrieveAllPostByDate(@RequestParam("date") String date) {
        //TODO
        return temporaryPosts;
    }

    @GetMapping("/{category_id}/posts")
    @Operation(
            summary = "Retrieve all posts ordered by date endpoint",
            description = "Returns a list of all posts"
    )
    public List<Post> retrieveAllPostByCategory(
            @Parameter (description="Category id wanted")
            @PathVariable UUID category_id
    ) {
        //TODO
        return temporaryPosts;
    }
}
