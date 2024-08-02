package com.example.blog.rest.api.service;

import com.example.blog.rest.api.entity.Comment;
import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.repository.CommentRepository;
import com.example.blog.rest.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id not found: " + commentId));
    }

    @Override
    public Comment updateComment(Long commentId, Comment comment) {
        Comment foundComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id not found: " + commentId));
        foundComment.setName(comment.getName());
        foundComment.setEmail(comment.getEmail());
        foundComment.setBody(comment.getBody());

        if (comment.getPost() != null) {
            Post post = postRepository
                    .findById(comment.getPost().getId())
                    .orElseThrow(() -> new RuntimeException("Post with id not found: " + comment.getPost().getId()));
            foundComment.setPost(post);
        }

        return commentRepository.save(foundComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id not found: " + commentId));
        commentRepository.deleteById(commentId);
    }
}
