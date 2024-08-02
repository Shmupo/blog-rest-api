package com.example.blog.rest.api.service;

import com.example.blog.rest.api.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, Comment comment);
    List<Comment> getAllComments();
    Comment getCommentById(Long postId, Long commentId);
    Comment updateComment(Long postId, Long commentId, Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    void deleteComment(Long postId, Long commentId);
}
