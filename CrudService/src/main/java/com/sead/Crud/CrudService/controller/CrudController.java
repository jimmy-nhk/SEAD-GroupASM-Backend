package com.sead.Crud.CrudService.controller;

import com.sead.Crud.CrudService.dto.CommentDTO;
import com.sead.Crud.CrudService.dto.PostDTO;
import com.sead.Crud.CrudService.dto.UserCommentDTO;
import com.sead.Crud.CrudService.dto.UserDTO;
import com.sead.Crud.CrudService.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/crud")
public class CrudController {
    @Autowired
    CrudService crudService;

    // CRUD

    /**User***************************************************/
    @GetMapping(path = "/getUser/id={id}")
    public UserDTO getUserById(@PathVariable long id){
        return crudService.getUserById(id);
    }


    /**Post***************************************************/
        // Get Mapping
    @GetMapping(path = "/getPost/id={id}")
    public PostDTO getPostById(@PathVariable long id){
        return crudService.getPostById(id);
    }

    @GetMapping(path = "/getPost/pageNo={pageNo}&pageSize={pageSize}&sortby={sortName}&asc={isAsc}")
    public Page<PostDTO> getAllPost(@PathVariable int pageNo, @PathVariable int pageSize,
                                    @PathVariable String sortName, @PathVariable boolean isAsc){
        return crudService.getALlPosts(pageNo, pageSize, sortName, isAsc);
    }


    @PostMapping(path = "/createPost")
    public PostDTO createPost(@RequestBody PostDTO postDTO){

        return crudService.createPost(postDTO);
    }

    @DeleteMapping(path = "/deletePost/id={postId}")
    public String deletePostById(@PathVariable Long postId){

        return crudService.deletePost(postId);
    }

    // update post
    @PutMapping(path = "/updatePost")
    public String updatePost(@RequestBody PostDTO postDTO){

        return crudService.updatePost(postDTO);
    }

    @PostMapping(value = "/like/pid={pid}&uid={uid}")
    public boolean likePost(@PathVariable Long pid, @PathVariable Long uid){

        return crudService.likePost(pid, uid);
    }

    @DeleteMapping(value = "/unlike/pid={pid}&uid={uid}")
    public boolean unlikePost(@PathVariable Long pid, @PathVariable Long uid){

        return crudService.unlikePost(pid, uid);
    }


    /**Post***************************************************/

    /**Comment***************************************************/
    @PostMapping(path = "/createComment")
    public UserCommentDTO createComment(@RequestBody CommentDTO commentDTO){

        return crudService.createComment(commentDTO);
    }

    // delete comment
    @DeleteMapping(path = "/deleteComment/id={commentId}")
    public String deleteCommentById(@PathVariable Long commentId){

        return crudService.deleteCommentById(commentId);
    }

    // update comment
    @PutMapping(path = "/updateComment/id={commentId}/body={body}")
    public String updateComment(@PathVariable Long commentId, @PathVariable String body){

        return crudService.updateComment(commentId , body);
    }

    @GetMapping("/getAllComments/postId={postId}")
    public List<UserCommentDTO> getAllCommentsWithUserBasedOnPostId(@PathVariable Long postId){

        return crudService.getAllCommentsWithUserBasedOnPostId(postId);
    }



}
