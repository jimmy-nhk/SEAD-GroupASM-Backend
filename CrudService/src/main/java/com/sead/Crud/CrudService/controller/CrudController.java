package com.sead.Crud.CrudService.controller;

import com.sead.Crud.CrudService.dto.PostDTO;
import com.sead.Crud.CrudService.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/crud")
public class CrudController {
    @Autowired
    CrudService crudService;

    // CRUD
        // Get Mapping
    @GetMapping(path = "/getPost/id={id}")
    public PostDTO getPostById(@PathVariable long id){
        return crudService.getPostById(id);
    }

    @PostMapping(path = "/createPost")
    public PostDTO createPost(@RequestBody PostDTO postDTO){

        return crudService.createPost(postDTO);
    }

    @DeleteMapping(path = "/deletePost/id={postId}")
    public String deletePostById(@PathVariable Long postId){

        return crudService.deletePost(postId);
    }



    //FIXME: fail updating post
    @PutMapping(path = "/updatePost")
    public String updatePost(@RequestBody PostDTO postDTO){

        return crudService.updatePost(postDTO);
    }
}
