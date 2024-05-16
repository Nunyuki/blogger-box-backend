package com.dauphine.blogger.services.impl;

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
    //private final List<Category> temporaryCategories;

    public CategoryServiceImpl(CategoryRepository repository){
        /*temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));*/

        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        //return temporaryCategories;

        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        //return temporaryCategories.stream().filter(category -> id.equals(category.getId())).findFirst().orElse(null);
        //return repository.findById(id).orElse(null);

        final Optional<Category> categoryOptional = repository.findById(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public Category create(String name) {
        /*Category category = new Category(UUID.randomUUID(),name);
        temporaryCategories.add(category);
        return category;*/

        Category category = new Category(name);
        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String name) {
        /*Category category = temporaryCategories.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
        if(category != null){
            category.setTitle(title);
        }
        return category;*/

        Category category = getById(id);
        if(category == null){
            return null;
        }
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public void deleteById(UUID id) {
        //temporaryCategories.removeIf(category -> id.equals(category.getId()));

        repository.deleteById(id);
    }

    @Override
    public List<Category> findAllLikeName(String name) {
        return repository.findAllLikeTitle(name);
    }
}
