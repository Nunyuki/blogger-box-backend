package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.models.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    List<Category> temporaryCategories = new ArrayList<>();

    @GetMapping("/")
    @Operation(
            summary = "Retrieve all categories endpoint",
            description = "Returns a list of all categories"
    )
    public List<Category> retrieveAllCategories() {
        return temporaryCategories;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve specific category endpoint",
            description = "Returns a specific category by {id}"
    )
    public Category retrieveCategory(
            @Parameter(description = "id of the category")
            @PathVariable UUID id) {
        //TODO
        return null;
    }

    @PostMapping("/")
    @Operation(
            summary = "Create a new category endpoint",
            description = "Create a new category '{title}' by request body"
    )
    public String createCategory(
            @Parameter(description = "Title of the category")
            @RequestBody CreationCategoryRequest categoryRequest) {

        Category category = new Category(UUID.randomUUID());
        category.setTitle(categoryRequest.getTitle());
        temporaryCategories.add(category);

        return "Create a category with \nid = '%S'\ntitle = '%s'\n" .formatted(category.getId(),category.getTitle());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category's title endpoint",
            description = "Update '{title}' by request body"
    )
    public String updateTitle(
            @Parameter(description = "Give the new title of the category")
            @PathVariable UUID id,
            @RequestBody UpdateCategoryRequest categoryRequest){
        //TO DO
        return "Update category '%s' with title '%s'".formatted(id, categoryRequest.getTitle());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete category endpoint",
            description = "Delete a category by '{id}'"
    )
    public String delete(
            @Parameter(description = "Give the id of the category to delete")
            @PathVariable UUID id){
        // TO DO
        return "Delete category '%s' with the title".formatted(id,"");
    }
}
