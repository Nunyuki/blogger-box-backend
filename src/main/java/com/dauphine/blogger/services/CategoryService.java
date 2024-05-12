package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> retrieveAllCategories();
    Category retrieveCategory(UUID id);
    Category createCategory(String title);
    Category updateTitle(UUID id, String title);
    void deleteCategory(UUID id);


    List<Category> findAllLikeTitle(String title);
}
