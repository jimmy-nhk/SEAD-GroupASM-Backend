package com.example.CommentService.service;

import com.example.CommentService.model.Comment;
import com.example.CommentService.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service()
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }


    // iterate through evey sub comments of the current post
    public List<Comment> getSubCommentsByParentId(Long commentId){

        List<Comment> allComments = commentRepository.findAll();

        List<Comment> allNeededComments = new ArrayList<>();

        try {
            Comment comment1 = commentRepository.findById(commentId).get();
            System.out.println("getSubCommentsByParentId: before loop comment: " + comment1.getId() + " , parentId: " + comment1.getParentId());

        } catch (Exception e){

        }

        //iterate through the list
        for (Comment comment: allComments
             ) {

            try{
                // check the commentId
                if(comment.getParentId() == commentId){

                    System.out.println("getSubCommentsByParentId: comment: " + comment.getId() + " , parentId: " + comment.getParentId());


                    // add the current comment
                    allNeededComments.add(comment);
                    allNeededComments.addAll(getSubCommentsByParentId(comment.getId()));
                }


            } catch (Exception e){
                System.out.println("Cannot add the sub comment: " + comment.getId());
            }

        }

        // return the all needed comments
        return allNeededComments;

    }

    // get all comments based on post
    public List<Comment> getAllCommentsBasedOnPostId(Long postId){

        // get  the comment based on post id
        List<Comment> commentParentList = commentRepository.getCommentsByPostId(postId);

//        List<Comment> allSubComments ;
//        List<Comment> allComments = new ArrayList<>();
//
//        for (Comment comment: commentParentList){

//            // find all the sub comments
//
//            System.out.println("getAllCommentsBasedOnPostId: comment: " + comment.getId() + " , parentId: " + comment.getParentId());
//
//            allSubComments = getSubCommentsByPostId(comment.getId());
//
//            allComments.addAll(allSubComments);
//        }

        // add all the root comments
//        allComments.addAll(commentParentList);

        // return the full list
        return commentParentList;
    }

    // update comment
    public Comment updateComment(Comment comment){

        Comment commentDb = commentRepository.getById(comment.getId());

        commentDb.setParentId(comment.getParentId());
        // fix these two only
        commentDb.setBody(comment.getBody());

        // get the current local date time
        LocalDateTime lt
                = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String text = lt.format(formatter);


        comment.setDatePosted(text);


        return commentRepository.save(commentDb);
    }

    // create comment
    public Comment createComment(Comment comment){

        // get the current local date time
        LocalDateTime lt
                = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String text = lt.format(formatter);


        comment.setDatePosted(text);
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long commentId){

        return commentRepository.getById(commentId);
    }

    // delete comment by post
    public String deleteCommentByPost(Long postId){

        // fetch all the comments inside the post
        List<Comment> allCommentsFromPost = getAllCommentsBasedOnPostId(postId);

        try {
            commentRepository.deleteAll(allCommentsFromPost);

            return "Successfully delete all comments in the post";
        }catch (Exception e){

            return "Cannot delete the comments in the post";
        }

    }

    // delete specific comment
    public String deleteCommentById(Long commentId){

        try {
            Comment comment = commentRepository.findById(commentId).get();

            // find all the sub comments
            List<Comment> allSubComments = getSubCommentsByParentId(comment.getId());

            // add the root comment
            allSubComments.add(comment);

            commentRepository.deleteAll(allSubComments);
            return "Successfully delete all comment with id " + commentId;
        } catch (Exception e){

            return "Cannot delete the comment with id: " + commentId;
        }
    }
}
