package com.sead.PostService.controller;


import com.sead.PostService.dto.PostDTO;
import com.sead.PostService.model.Post;
import com.sead.PostService.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    // Get Mapping
    @GetMapping(path = "/get/category={category}/pageNo={pageNo}&pageSize={pageSize}&sortBy={sortName}")
    public Page<Post> getAllPostByCategory(@PathVariable String category, @PathVariable int pageNo, @PathVariable int pageSize,
                                 @PathVariable String sortName){
        return postService.findAllPostsByCategory(category,pageNo, pageSize, sortName);
    }

    // Get Mapping
    @GetMapping(path = "/get/userId={userId}/pageNo={pageNo}&pageSize={pageSize}&sortBy={sortName}")
    public Page<Post> getAllPostByUserId(@PathVariable Long userId, @PathVariable int pageNo, @PathVariable int pageSize,
                                           @PathVariable String sortName){
        return postService.findAllPostsByUser(userId,pageNo, pageSize, sortName);
    }

    @GetMapping(path = "/get/pageNo={pageNo}&pageSize={pageSize}&asc={isAsc}")
    public Page<Post> getAllPost(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable boolean isAsc){
        return postService.getALlPostsNoFilter(pageNo, pageSize, isAsc);
    }

    @GetMapping(path = "/get-pov/uid={uid}&pageNo={pageNo}&pageSize={pageSize}&sortby={sortName}&asc={isAsc}")
    public List<PostDTO> getAllPost(@PathVariable Long uid, @PathVariable int pageNo,
                                    @PathVariable int pageSize, @PathVariable String sortName,
                                    @PathVariable boolean isAsc){
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post: postService.getALlPosts(pageNo, pageSize, sortName, isAsc)){
            postDTOS.add(post.toPostDTOUserPOV(uid));
        }
        return postDTOS;
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
