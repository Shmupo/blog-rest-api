package com.example.blog.rest.api.controller;

import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.payload.PostResponse;
import com.example.blog.rest.api.service.PostService;
import com.example.blog.rest.api.utils.AppConstants;
import jakarta.validation.Valid;
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
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        Post data = postService.createPost(post);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/posts?pageNo=0&pageSize=5&SortBy=id&sortDir=desc

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
        //List<Post> data = postService.getAllPosts();
        PostResponse data = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
        Post data = postService.getPostById(postId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") Long postid,
                                           @Valid @RequestBody Post post) {
        Post data = postService.updatePost(postid, post);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public HttpStatus deletePost(@PathVariable("postId") Long postid) {
        postService.deletePost(postid);
        return HttpStatus.OK;
    }
}
