package com.sead.PostService.controller;


import com.sead.PostService.model.Post;
import com.sead.PostService.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    // Post Mapping
    @PostMapping(path = "/create")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

        // For testing
    @PostMapping(path = "/many-create")
    public List<Post> createPosts(@RequestBody List<Post> posts){
        return postService.createPosts(posts);
    }

    // Get Mapping


    // Put Mapping


    // Delete Mapping


}
