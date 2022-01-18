package com.sead.PostService.service;

import com.sead.PostService.model.LikedUser;
import com.sead.PostService.model.Post;
import com.sead.PostService.repository.LikedUserRepository;
import com.sead.PostService.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final String POST_CACHE = "POST";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Post> hashOperations;


    @PostConstruct
    private void initializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void saveToCache(Post employee) {
        hashOperations.put(POST_CACHE, employee.getId(), employee);
    }

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikedUserRepository likedUserRepository;

    Logger logger = LoggerFactory.getLogger(PostService.class);

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> createPosts(List<Post> posts) {
        return postRepository.saveAll(posts);
    }

    public Post findPostByIdRedis(Long id){
        Post post = hashOperations.get(POST_CACHE, id);

        if (post != null) return post;

        saveToCache(postRepository.findById(id).get());
        return postRepository.findById(id).get();

    }

    public Post updatePostRedis(Post post) {

        try {
            Optional<Post> possiblePost = postRepository.findById(post.getId());
            if (possiblePost.isPresent()) {
                saveToCache(possiblePost.get());
                return postRepository.save(post);
            } else {
                return null;
            }
        } catch (Exception e) {
            // Id null
            return null;
        }
    }
    public Post updatePost(Post post) {

//        Post existPost = postRepository.findById(post.getId()).orElse(null);
//        assert existPost != null;
//
//        existPost.setTitle(post.getTitle());
//        existPost.setCategory(post.getCategory());
//        existPost.setDirectors(post.getDirectors());
//        existPost.setBodyText(post.getBodyText());
//        existPost.setLikedCount(post.getLikedCount());
//        existPost.setThumbnailURL(post.getThumbnailURL());
//        existPost.setViewCount(post.getViewCount());
//        System.out.println("Updated post:" + existPost.toString());
//        return postRepository.save(existPost);

        try {
            Optional<Post> possiblePost = postRepository.findById(post.getId());
            if (possiblePost.isPresent()) {
                return postRepository.save(post);
            } else {
                return null;
            }
        } catch (Exception e) {
            // Id null
            return null;
        }
    }

    public List<Post> updatePosts(List<Post> posts) {
        List<Post> updatedPosts = new ArrayList<>();
        for (Post post : posts) {
            updatedPosts.add(updatePost(post));
        }
        return updatedPosts;
    }

    public boolean deletePost(Long postId) {
        Optional<Post> possiblePost = postRepository.findById(postId);
        if (possiblePost.isPresent()) {
            postRepository.delete(possiblePost.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean deletePost(Post post) {
        try {
            Optional<Post> possiblePost = postRepository.findById(post.getId());
            if (possiblePost.isPresent()) {
                postRepository.delete(possiblePost.get());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deletePosts(List<Post> posts) {
        for (Post post : posts) {
            try {
                deletePost(post.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Post getPostById(Long postId) {
        Optional<Post> possiblePost = postRepository.findById(postId);
        if (possiblePost.isPresent()) {
            return possiblePost.get();
        } else {
            return null;
        }
    }

    public Page<Post> findAllPostsByCategory(String category, int pageNo, int pageSize, String sortName) {

        return postRepository.findAllByCategoryEquals(PageRequest.of(pageNo, pageSize, Sort.by(sortName).descending()), category);
    }

    public Page<Post> findAllPostsByUser(Long userId, int pageNo, int pageSize, String sortName) {

        return postRepository.findAllByUserIdEquals(PageRequest.of(pageNo, pageSize, Sort.by(sortName).descending()), userId);
    }

    public Page<Post> getALlPosts(int pageNo, int pageSize, String sortName, boolean isAsc) {
        if (isAsc) {
            return postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortName).ascending()));
        } else {
            return postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortName).descending()));
        }
    }

    public Page<Post> getALlPostsNoFilter(int pageNo, int pageSize, boolean isAsc) {
        if (isAsc) {
            return postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id")));
        } else {
            return postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        }
    }

    public boolean likePost(Long postId, Long userId) {
        Post post = getPostById(postId);
        if (post != null) {
            post.setLikedCount(post.getLikedCount() + 1);
            post.getLikedUserList().add(LikedUser.builder()
                    .post(post)
                    .uid(userId)
                    .build());
            try {
                postRepository.save(post);
            } catch (Exception e) {
                this.logger.error("Duplicating like status");
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean unlikePost(Long postId, Long userId) {
        Post post = getPostById(postId);
        if (post != null) {
            try {
                likedUserRepository.delete(likedUserRepository.findLikedUserByPostIdAndUid(postId, userId));
                post.setLikedCount(post.getLikedCount() - 1);
                postRepository.save(post);
            } catch (Exception e) {
                this.logger.error("Duplicating unlike behavior");
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean toggleLike(Long postId, Long userId) {
        Post post = getPostById(postId);
        LikedUser likedUser = likedUserRepository.findLikedUserByPostIdAndUid(postId, userId);
        if (likedUser == null) {
            return likePost(postId, userId);
        } else {
            return unlikePost(postId, userId);
        }
    }

    public void viewPost(Long postId) {
        Post post = getPostById(postId);
        post.setViewCount(post.getLikedCount() + 1);
        updatePost(post);
    }

}
