package com.example.blog.rest.api.service.Imp;

import com.example.blog.rest.api.entity.Comment;
import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.exception.BlogAPIException;
import com.example.blog.rest.api.exception.ResourceNotFoundException;
import com.example.blog.rest.api.repository.CommentRepository;
import com.example.blog.rest.api.repository.PostRepository;
import com.example.blog.rest.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Long postId, Comment comment) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("posts", "postId", postId));

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
                .orElseThrow(() -> new ResourceNotFoundException("posts", "postId", postId));

        Comment foundComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comments", "commentId", commentId));

        if(!foundComment.getPost().getId().equals(foundPost.getId())) { // if post does not match comment
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post.");
        }

        return foundComment;
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment comment) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("posts", "postId", postId));

        Comment foundComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comments", "commentId", commentId));

        if(foundComment.getPost().getId().equals(foundPost.getId())) { // if post does not match comment
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post.");
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
                .orElseThrow(() -> new ResourceNotFoundException("posts", "postId", postId));

        Comment foundComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comments", "commentId", commentId));

        if(foundComment.getPost().getId().equals(foundPost.getId())) { // if post does not match comment
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post.");
        }

        // remove comment from post comments
        postRepository.save(foundPost);

        commentRepository.deleteById(commentId);
    }
}
