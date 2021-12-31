package com.sead.PostService.controller;


import com.sead.PostService.dto.PostDTOUserPOV;
import com.sead.PostService.model.Post;
import com.sead.PostService.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;
    //CRUD

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
    @GetMapping(path = "/get/pageNo={pageNo}&pageSize={pageSize}&sortby={sortName}&asc={isAsc}")
    public Page<Post> getAllPost(@PathVariable int pageNo, @PathVariable int pageSize,
                                 @PathVariable String sortName, @PathVariable boolean isAsc){
        return postService.getALlPosts(pageNo, pageSize, sortName, isAsc);
    }

    @GetMapping(path = "/get-pov/uid={uid}&pageNo={pageNo}&pageSize={pageSize}&sortby={sortName}&asc={isAsc}")
    public List<PostDTOUserPOV> getAllPost(@PathVariable Long uid, @PathVariable int pageNo,
                                           @PathVariable int pageSize, @PathVariable String sortName,
                                           @PathVariable boolean isAsc){
        List<PostDTOUserPOV> postDTOUserPOVs = new ArrayList<>();
        for (Post post: postService.getALlPosts(pageNo, pageSize, sortName, isAsc)){
            postDTOUserPOVs.add(post.toPostDTOUserPOV(uid));
        }
        return postDTOUserPOVs;
    }

    @GetMapping(path = "/get/id={id}")
    public Post getPostById(@PathVariable Long id){
//        postService.viewPost(id);
        return postService.getPostById(id);
    }

        // Put Mapping
    @PutMapping(path = "/update")
    public Post updatePost(@RequestBody Post post){
        return postService.updatePost(post);
    }

    @PutMapping(path = "/many-update")
    public List<Post> updatePost(@RequestBody List<Post> posts){
        return postService.updatePosts(posts);
    }

        // Delete Mapping
    @DeleteMapping(path = "/delete/id={id}")
    public boolean deletePost(@PathVariable Long id){
        return postService.deletePost(id);
    }

    @DeleteMapping(path = "/delete")
    public boolean deletePost(@RequestBody Post post){
        return postService.deletePost(post);
    }

    @DeleteMapping(path = "/many-delete")
    public void deletePost(@RequestBody List<Post> posts){
        postService.deletePosts(posts);
    }

    // MISC

    @PostMapping(path = "/like/pid={pid}&uid={uid}")
    public boolean likePost(@PathVariable Long pid, @PathVariable Long uid){
        return postService.likePost(pid, uid);
    }

    @DeleteMapping(path = "/like/pid={pid}&uid={uid}")
    public boolean unlikePost(@PathVariable Long pid, @PathVariable Long uid){
        return postService.unlikePost(pid, uid);
    }

    @PutMapping(path = "/like/pid={pid}&uid={uid}")
    public boolean toggleLikePost(@PathVariable Long pid, @PathVariable Long uid){
        return postService.toggleLike(pid, uid);
    }






}
