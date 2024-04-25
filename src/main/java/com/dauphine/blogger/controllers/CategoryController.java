package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping("/")
    @Operation(
            summary = "Retrieve all categories endpoint",
            description = "Returns a list of all categories"
    )
    public List<Category> retrieveAllCategories() {
        return service.retrieveAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve specific category endpoint",
            description = "Returns a specific category by {id}"
    )
    public Category retrieveCategory(
            @Parameter(description = "id of the category")
            @PathVariable UUID id) {
        return service.retrieveCategory(id);
    }

    @PostMapping("/")
    @Operation(
            summary = "Create a new category endpoint",
            description = "Create a new category '{title}' by request body"
    )
    public Category createCategory(
            @Parameter(description = "Title of the category")
            @RequestBody CreationCategoryRequest categoryRequest) {

        return service.createCategory(categoryRequest.getTitle());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category's title endpoint",
            description = "Update '{title}' by request body"
    )
    public Category updateTitle(
            @Parameter(description = "Give the new title of the category")
            @PathVariable UUID id,
            @RequestBody UpdateCategoryRequest categoryRequest){

        return service.updateTitle(id,categoryRequest.getTitle());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete category endpoint",
            description = "Delete a category by '{id}'"
    )
    public void deleteCategory(
            @Parameter(description = "Give the id of the category to delete")
            @PathVariable UUID id){
        service.deleteCategory(id);
    }
}
