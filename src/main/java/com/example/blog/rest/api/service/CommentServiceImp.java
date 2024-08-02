package com.example.blog.rest.api.service;

import com.example.blog.rest.api.entity.Comment;
import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.repository.CommentRepository;
import com.example.blog.rest.api.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, Comment comment) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with id not found: " + postId));

        comment.setPost(foundPost);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with id not found: " + postId));

        Comment foundComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id not found: " + commentId));

        if(foundComment.getPost().getId().equals(foundPost.getId())) { // if post does not match comment
            throw new RuntimeException("Comment does not belong the post");
        }

        return foundComment;
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment comment) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with id not found: " + postId));

        Comment foundComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id not found: " + commentId));

        if(foundComment.getPost().getId().equals(foundPost.getId())) { // if post does not match comment
            throw new RuntimeException("Comment does not belong the post");
        }

        foundComment.setName(comment.getName());
        foundComment.setEmail(comment.getEmail());
        foundComment.setBody(comment.getBody());
        foundComment.setPost(foundPost);

        return commentRepository.save(foundComment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with id not found: " + postId));

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment with id not found: " + commentId));

        // remove comment from post comments
        postRepository.save(foundPost);

        commentRepository.deleteById(commentId);
    }
}
