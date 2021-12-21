package com.sead.PostService.service;

import com.sead.PostService.model.LikedUser;
import com.sead.PostService.model.Post;
import com.sead.PostService.repository.LikedUserRepository;
import com.sead.PostService.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikedUserRepository likedUserRepository;

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public List<Post> createPosts(List<Post> posts){
        return postRepository.saveAll(posts);
    }

    public Post updatePost(Post post){

        Post existPost = postRepository.findById(post.getId()).orElse(null);
        assert existPost != null;

        existPost.setTitle(post.getTitle());
        existPost.setCategory(post.getCategory());
        existPost.setDirectors(post.getDirectors());
        existPost.setBodyText(post.getBodyText());
        existPost.setLikedCount(post.getLikedCount());
        existPost.setThumbnailURL(post.getThumbnailURL());
        existPost.setViewCount(post.getViewCount());
        System.out.println("Updated post:" + existPost.toString());
        return postRepository.save(existPost);
    }

    public List<Post> updatePosts(List<Post> posts){
        List<Post> updatedPosts = new ArrayList<>();
        for (Post post: posts){
            updatedPosts.add(updatePost(post));
        }
        return updatedPosts;
    }

    public boolean deletePost(Long postId){
        Optional<Post> possiblePost = postRepository.findById(postId);
        if (possiblePost.isPresent()){
            postRepository.delete(possiblePost.get());
            return true;
        } else{
            return false;
        }
    }

    public void deletePosts(List<Post> posts){
        for (Post post: posts) {
            deletePost(post.getId());
        }
    }

    public Post getPostById(Long postId){
        Optional<Post> possiblePost = postRepository.findById(postId);
        if (possiblePost.isPresent()){
            return possiblePost.get();
        } else{
            return null;
        }
    }

    public Page<Post> getALlPosts(int pageNo, int pageSize, String sortName, boolean isAsc){
        if (isAsc){
            return postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortName).ascending()));
        } else{
            return postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortName).descending()));
        }
    }

    public void likePost(Long postId, Long userId){
        Post post = getPostById(postId);
        post.setLikedCount(post.getLikedCount() + 1);
        post.getLikedUserList().add(LikedUser.builder()
                .post(post)
                .uid(userId)
                .build());
        postRepository.save(post);
    }

    public void viewPost(Long postId){
        Post post = getPostById(postId);
        post.setViewCount(post.getLikedCount()+1);
        updatePost(post);
    }

}
