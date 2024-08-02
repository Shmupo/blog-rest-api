package com.example.blog.rest.api.service;

import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.exception.ResourceNotFoundException;
import com.example.blog.rest.api.repository.CommentRepository;
import com.example.blog.rest.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("posts", "postId", postId));
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("posts", "postId", postId));

        foundPost.setTitle(post.getTitle());
        foundPost.setContent(post.getContent());
        foundPost.setDescription(post.getDescription());

        return postRepository.save(foundPost);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("posts", "postId", postId));

        postRepository.deleteById(postId);
    }
}
