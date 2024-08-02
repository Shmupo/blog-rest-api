package com.example.blog.rest.api.service;

import com.example.blog.rest.api.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    List<Comment> getAllComments();
    Comment getCommentById(Long commentId);
    Comment updateComment(Long commentId, Comment comment);
    void deleteComment(Long commentId);
}
