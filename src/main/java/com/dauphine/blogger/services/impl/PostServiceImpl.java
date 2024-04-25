package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> temporaryPost;

    public PostServiceImpl(){
        temporaryPost = new ArrayList<>();
        Category category1 = new Category(UUID.randomUUID(),"Category1");
        Category category2 = new Category(UUID.randomUUID(),"Category2");

        temporaryPost.add(new Post(UUID.randomUUID(), "my first post","content 1",category1.getId()));
        temporaryPost.add(new Post(UUID.randomUUID(), "my second post","content 2",category2.getId()));
        temporaryPost.add(new Post(UUID.randomUUID(), "my third post","content 3",category1.getId()));
    }

    @Override
    public List<Post> retrieveAllPostByCategory(Category category) {
        List<Post> postByCategory = new ArrayList<>();
        for(Post p:temporaryPost){
            if (p.getCategory()==category){
                postByCategory.add(p);
            }
        }
        return postByCategory;
    }

    @Override
    public List<Post> retrieveAllPostByDate() {
        return temporaryPost;
    }

    @Override
    public Post retrievePost(UUID id) {
        return temporaryPost.stream().filter(post -> id.equals(post.getId())).findFirst().orElse(null);
    }

    @Override
    public Post createPost(String title, String content, UUID category_id) {
        Post post = new Post(UUID.randomUUID(),title,content,category_id);
        temporaryPost.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = temporaryPost.stream().filter(p -> id.equals(p.getId())).findFirst().orElse(null);
        if(post != null){
            post.setTitle(title);
            post.setContent(content);
            post.setCreated_date();
        }
        return post;
    }

    @Override
    public void delete(UUID id) {
        temporaryPost.removeIf(post -> id.equals(post.getId()));
    }
}
