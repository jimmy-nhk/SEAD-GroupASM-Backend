package com.example.CommentService.service;

import com.example.CommentService.model.Comment;
import com.example.CommentService.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service()
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentByPostId(Long postId){

        return commentRepository.getAllByPostId(postId);

    }

    public Comment updateComment(Comment comment){

        Comment commentDb = commentRepository.getById(comment.getId());

        // fix these two only
        commentDb.setBody(comment.getBody());
        commentDb.setDatePosted(comment.getDatePosted());

        return commentRepository.save(commentDb);
    }

    public Comment createComment(Comment comment){

        return commentRepository.save(comment);
    }

    public String deleteCommentById(Long commentId){

        try {
            commentRepository.deleteById(commentId);
            return "Successfully delete comment";
        } catch (Exception e){

            return "Cannot delete the comment";
        }
    }
}
