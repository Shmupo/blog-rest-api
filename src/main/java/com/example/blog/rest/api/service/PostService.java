package com.example.blog.rest.api.service;

import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.payload.PostResponse;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    //List<Post> getAllPosts();
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    Post getPostById(Long postId);
    Post updatePost(Long postId, Post post);
    void deletePost(Long postId);
}
