package com.example.blog.rest.api.repository;

import com.example.blog.rest.api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
