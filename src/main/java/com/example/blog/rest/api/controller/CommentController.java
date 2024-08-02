package com.example.blog.rest.api.controller;

import com.example.blog.rest.api.entity.Comment;
import com.example.blog.rest.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment data = commentService.createComment(comment);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> data = commentService.getAllComments();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long commentId) {
        Comment data = commentService.getCommentById(commentId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long commentId, @RequestBody Comment comment) {
        Comment data = commentService.updateComment(commentId, comment);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteComment(@PathVariable("id") Long commentId) {
        commentService.deleteComment(commentId);
        return HttpStatus.OK;
    }


}
