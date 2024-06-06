package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exception.CategoryNameAlreadyExistsException;
import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository){
                this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
       return repository.findAll();
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return repository.findById(id).
                orElseThrow(()-> new CategoryNotFoundByIdException(id));
    }

    @Override
    public Category create(String name) throws CategoryNameAlreadyExistsException{
        if(!repository.findAllLikeTitle(name).isEmpty()){
            throw new CategoryNameAlreadyExistsException(name);
        }
        Category category = new Category(name);
        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String name) throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException {
        Category category = getById(id);
        if (!repository.findAllLikeTitle(name).isEmpty()){
            throw new CategoryNameAlreadyExistsException(name);
        }
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public boolean deleteById(UUID id) throws CategoryNotFoundByIdException{
        getById(id);
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> findAllLikeName(String name) {
        return repository.findAllLikeTitle(name);
    }
}
