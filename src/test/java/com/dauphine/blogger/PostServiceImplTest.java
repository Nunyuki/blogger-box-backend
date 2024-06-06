package com.dauphine.blogger;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.exception.CategoryNotFoundByIdException;
import com.dauphine.blogger.exception.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceImplTest {
    private PostRepository postRepository;
    private CategoryService categoryService;
    private PostServiceImpl postService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        categoryService = mock(CategoryService.class);
        postService = new PostServiceImpl(postRepository, categoryService);
    }

    @Test
    void shouldReturnPostsWhenCategoryIdExists() throws CategoryNotFoundByIdException {
        // Arrange
        Category category = new Category("Category");
        category.setId(UUID.randomUUID());
        List<Post> expectedPosts = List.of(new Post(UUID.randomUUID(), "Title1", "Content1", category, new Date()));
        when(categoryService.getById(category.getId())).thenReturn(category);
        when(postRepository.findAllByCategory(category)).thenReturn(expectedPosts);

        // Act
        List<Post> actualPosts = postService.getAllByCategoryId(category);

        // Assert
        assertEquals(expectedPosts, actualPosts);
    }

    @Test
    void shouldThrowExceptionWhenCategoryIdDoesNotExist() throws CategoryNotFoundByIdException {
        // Arrange
        Category category = new Category("Category");
        category.setId(UUID.randomUUID());
        when(categoryService.getById(category.getId())).thenThrow(new CategoryNotFoundByIdException(category.getId()));

        // Act & Assert
        assertThrows(CategoryNotFoundByIdException.class, () -> postService.getAllByCategoryId(category));
    }

    @Test
    void shouldReturnAllPosts() {
        // Arrange
        List<Post> expectedPosts = List.of(new Post(UUID.randomUUID(), "Title1", "Content1", new Category("Category"), new Date()));
        when(postRepository.findAll()).thenReturn(expectedPosts);

        // Act
        List<Post> actualPosts = postService.getAll();

        // Assert
        assertEquals(expectedPosts, actualPosts);
    }

    @Test
    void shouldReturnPostWhenIdExists() throws PostNotFoundByIdException {
        // Arrange
        UUID id = UUID.randomUUID();
        Post expectedPost = new Post(id, "Title", "Content", new Category("Category"), new Date());
        when(postRepository.findById(id)).thenReturn(Optional.of(expectedPost));

        // Act
        Post actualPost = postService.getById(id);

        // Assert
        assertEquals(expectedPost, actualPost);
    }

    @Test
    void shouldThrowExceptionWhenPostIdDoesNotExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PostNotFoundByIdException.class, () -> postService.getById(id));
    }

    @Test
    void shouldCreatePost() throws CategoryNotFoundByIdException {
        // Arrange
        UUID categoryId = UUID.randomUUID();
        CreationPostRequest postRequest = new CreationPostRequest("Title", "Content", categoryId);
        Category category = new Category("Category");
        category.setId(categoryId);
        Post expectedPost = new Post(UUID.randomUUID(), "Title", "Content", category, new Date());

        when(categoryService.getById(categoryId)).thenReturn(category);
        when(postRepository.save(any(Post.class))).thenReturn(expectedPost);

        // Act
        Post actualPost = postService.create(postRequest);

        // Assert
        assertEquals(expectedPost, actualPost);
    }

    @Test
    void shouldUpdatePostWhenIdExists() throws PostNotFoundByIdException {
        // Arrange
        UUID id = UUID.randomUUID();
        Post existingPost = new Post(id, "Old Title", "Old Content", new Category("Category"), new Date());
        when(postRepository.findById(id)).thenReturn(Optional.of(existingPost));
        when(postRepository.save(existingPost)).thenReturn(existingPost);

        // Act
        Post updatedPost = postService.update(id, "New Title", "New Content");

        // Assert
        assertEquals("New Title", updatedPost.getTitle());
        assertEquals("New Content", updatedPost.getContent());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentPost() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PostNotFoundByIdException.class, () -> postService.update(id, "Title", "Content"));
    }

    @Test
    void shouldDeletePostWhenIdExists() throws PostNotFoundByIdException {
        // Arrange
        UUID id = UUID.randomUUID();
        Post post = new Post(id, "Title", "Content", new Category("Category"), new Date());
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        // Act
        boolean isDeleted = postService.deleteById(id);

        // Assert
        assertEquals(true, isDeleted);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentPost() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PostNotFoundByIdException.class, () -> postService.deleteById(id));
    }

    @Test
    void shouldReturnPostsWithSimilarTitleOrContent() {
        // Arrange
        String title = "Title";
        String content = "Content";
        List<Post> expectedPosts = List.of(new Post(UUID.randomUUID(), title, content, new Category("Category"), new Date()));
        when(postRepository.findAllPostByTitleOrContent(title, content)).thenReturn(expectedPosts);

        // Act
        List<Post> actualPosts = postService.findAllPostByTitleOrContent(title, content);

        // Assert
        assertEquals(expectedPosts, actualPosts);
    }
}
