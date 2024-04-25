package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> temporaryCategories;

    public CategoryServiceImpl(){
        temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));


    }
    @Override
    public List<Category> retrieveAllCategories() {
        return temporaryCategories;
    }

    @Override
    public Category retrieveCategory(UUID id) {
        return temporaryCategories.stream().filter(category -> id.equals(category.getId())).findFirst().orElse(null);
    }

    @Override
    public Category createCategory(String title) {
        Category category = new Category(UUID.randomUUID(),title);
        temporaryCategories.add(category);
        return category;
    }

    @Override
    public Category updateTitle(UUID id, String title) {
        Category category = temporaryCategories.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
        if(category != null){
            category.setTitle(title);
        }
        return category;
    }

    @Override
    public void deleteCategory(UUID id) {
        temporaryCategories.removeIf(category -> id.equals(category.getId()));
    }
}