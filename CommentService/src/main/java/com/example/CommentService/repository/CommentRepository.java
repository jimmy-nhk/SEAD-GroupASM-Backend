package com.example.CommentService.repository;

import com.example.CommentService.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByPostId(Long postId);
}
