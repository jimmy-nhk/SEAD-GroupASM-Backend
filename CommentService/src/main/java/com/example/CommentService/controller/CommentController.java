package com.example.CommentService.controller;


import com.example.CommentService.model.Comment;
import com.example.CommentService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comments")
@CrossOrigin(value = "*" , allowedHeaders = "*")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    // fetch all comments based on post
    @GetMapping("/getAllComments/postId={postId}")
    public List<Comment> getAllCommentsBasedOnPostId(@PathVariable Long postId){

        return commentService.getAllCommentsBasedOnPostId(postId);
    }

    // delete the specific comments including its children
    @DeleteMapping("/deleteComments/commentId={commentId}")
    public String deleteCommentsByCommentId(@PathVariable Long commentId){

        return commentService.deleteCommentById(commentId);
    }

    // delete all the comments in the post
    @DeleteMapping("/deleteCommentsInPost/postId={postId}")
    public String deleteCommentsInPost(@PathVariable Long postId){

        return commentService.deleteCommentByPost(postId);
    }

    // get specific comment
    @GetMapping("/getComment/commentId={commentId}")
    public Comment getCommentById(@PathVariable Long commentId){

        return commentService.getCommentById(commentId);
    }

    // update comment
    @PutMapping("/updateComment/commentId={commentId}/body={body}")
    public Comment updateComment(@PathVariable Long commentId, @PathVariable String body){
        return commentService.updateComment(commentId, body);
    }

    // get specific comment
    @GetMapping("/getCommentRedis/commentId={commentId}")
    public Comment getCommentByIdRedis(@PathVariable Long commentId){

        return commentService.getCommentByIdRedis(commentId);
    }

    // update comment
    @PutMapping("/updateCommentRedis/commentId={commentId}/body={body}")
    public Comment updateCommentRedis(@PathVariable Long commentId, @PathVariable String body){
        return commentService.updateCommentRedis(commentId, body);
    }

    // create comment
    @PostMapping("/createComment")
    public Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }


}
