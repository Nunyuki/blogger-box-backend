package com.dauphine.blogger;

import com.dauphine.blogger.exception.CategoryNameAlreadyExistsException;
import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {
    private CategoryRepository repo;
    private CategoryService service;

    @BeforeEach
    void setUp(){
        repo = mock(CategoryRepository.class);
        service = new CategoryServiceImpl(repo);
    }

    @Test
    void shouldReturnCategoryWhenIdExists() throws CategoryNotFoundByIdException {
        // Arrange
        UUID id = UUID.randomUUID();
        Category expected = new Category("Category");
        when(repo.findById(id)).thenReturn(Optional.of(expected));

        // Act
        Category actual = service.getById(id);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist(){
        // Arrange
        UUID id = UUID.randomUUID();
        when(repo.findById(id)).thenReturn(Optional.empty());

        // Act
        CategoryNotFoundByIdException exception = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> service.getById(id)
        );

        // Assert
        assertEquals("Category with id " + id + " not found", exception.getMessage());
    }

    @Test
    void shouldReturnAllCategories() {
        // Arrange
        List<Category> expectedCategories = List.of(new Category("Category1"), new Category("Category2"));
        when(repo.findAll()).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = service.getAll();

        // Assert
        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    void shouldCreateCategoryWhenNameDoesNotExist() throws CategoryNameAlreadyExistsException {
        // Arrange
        String name = "New Category";
        Category expected = new Category(name);
        when(repo.findAllLikeTitle(name)).thenReturn(List.of());

        when(repo.save(any())).thenReturn(expected);

        // Act
        Category actual = service.create(name);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameAlreadyExists() {
        // Arrange
        String name = "Existing Category";
        when(repo.findAllLikeTitle(name)).thenReturn(List.of(new Category(name)));

        // Act
        CategoryNameAlreadyExistsException exception = assertThrows(
                CategoryNameAlreadyExistsException.class,
                () -> service.create(name)
        );

        // Assert
        assertEquals("Category with name " + name + " already exists", exception.getMessage());
    }

    @Test
    void shouldUpdateCategoryNameWhenIdExistsAndNameDoesNotExist() throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException {
        // Arrange
        UUID id = UUID.randomUUID();
        String oldName = "Old Category";
        String newName = "New Category";
        Category category = new Category(oldName);
        when(repo.findById(id)).thenReturn(Optional.of(category));
        when(repo.findAllLikeTitle(newName)).thenReturn(List.of());
        when(repo.save(category)).thenReturn(new Category(newName));

        // Act
        Category updatedCategory = service.updateName(id, newName);

        // Assert
        assertEquals(newName, updatedCategory.getName());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingWithExistingName() {
        // Arrange
        UUID id = UUID.randomUUID();
        String oldName = "Old Category";
        String newName = "Existing Category";
        Category category = new Category(oldName);
        when(repo.findById(id)).thenReturn(Optional.of(category));
        when(repo.findAllLikeTitle(newName)).thenReturn(List.of(new Category(newName)));

        // Act
        CategoryNameAlreadyExistsException exception = assertThrows(
                CategoryNameAlreadyExistsException.class,
                () -> service.updateName(id, newName)
        );

        // Assert
        assertEquals("Category with name " + newName + " already exists", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentCategory() {
        // Arrange
        UUID id = UUID.randomUUID();
        String newName = "New Category";
        when(repo.findById(id)).thenReturn(Optional.empty());

        // Act
        CategoryNotFoundByIdException exception = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> service.updateName(id, newName)
        );

        // Assert
        assertEquals("Category with id " + id + " not found", exception.getMessage());
    }

    @Test
    void shouldDeleteCategoryWhenIdExists() throws CategoryNotFoundByIdException {
        // Arrange
        UUID id = UUID.randomUUID();
        Category category = new Category("Category");
        when(repo.findById(id)).thenReturn(Optional.of(category));

        // Act
        boolean isDeleted = service.deleteById(id);

        // Assert
        assertEquals(true, isDeleted);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentCategory() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(repo.findById(id)).thenReturn(Optional.empty());

        // Act
        CategoryNotFoundByIdException exception = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> service.deleteById(id)
        );

        // Assert
        assertEquals("Category with id " + id + " not found", exception.getMessage());
    }

    @Test
    void shouldReturnCategoriesWithSimilarName() {
        // Arrange
        String name = "Cat";
        List<Category> expectedCategories = List.of(new Category("Category1"), new Category("Category2"));
        when(repo.findAllLikeTitle(name)).thenReturn(expectedCategories);

        // Act
        List<Category> actualCategories = service.findAllLikeName(name);

        // Assert
        assertEquals(expectedCategories, actualCategories);
    }
}
