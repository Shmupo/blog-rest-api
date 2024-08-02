package com.example.blog.rest.api.controller;

import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api/v1/posts") // good practice to make different versions available
@RequestMapping("/api/posts") // keeping it simple for now
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post data = postService.createPost(post);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> data = postService.getAllPosts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long postId) {
        Post data = postService.getPostById(postId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long postid, @RequestBody Post post) {
        Post data = postService.updatePost(postid, post);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePost(@PathVariable("id") Long postid) {
        postService.deletePost(postid);
        return HttpStatus.OK;
    }
}
