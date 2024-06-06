package com.dauphine.blogger.services;

import com.dauphine.blogger.exception.CategoryNameAlreadyExistsException;
import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getById(UUID id) throws CategoryNotFoundByIdException;
    Category create(String name) throws CategoryNameAlreadyExistsException;
    Category updateName(UUID id, String name) throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException;
    boolean deleteById(UUID id) throws CategoryNotFoundByIdException;
    List<Category> findAllLikeName(String name);
}
