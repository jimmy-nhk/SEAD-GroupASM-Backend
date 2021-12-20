package SEAD.POST.Service;


import SEAD.POST.Model.Post;
import SEAD.POST.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository repository;
    public Post addPost(Post post) {
        return this.repository.save(post);
    }

    public List<Post> addPosts(List<Post> posts) {
        return this.repository.saveAll(posts);
    }

    public List<Post> updPosts(List<Post> posts) {
        for (Post post : posts
        ) {
            updatePost(post);
        }
        return this.repository.findAll();
    }

    public void delPost(long post) {
        repository.deleteById(post);
    }

    public List<Post> delPosts(List<Post> posts) {
        for (Post post : posts
        ) {
            delPost(post.getPid());
        }
        return this.repository.findAll();
    }

    public List<Post> getAllPosts() {
        return this.repository.findAll();
    }

    public Post getPost(long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void updatePost(Post post) {
        Post existPost = repository.findById(post.getPid()).orElse(null);
        assert existPost != null;

        existPost.setTitle(post.getTitle());
        existPost.setCategory(post.getCategory());
        existPost.setDirectors(post.getDirectors());
        existPost.setBodyText(post.getBodyText());
        existPost.setLikedCount(post.getLikedCount());
        existPost.setThumbnailURL(post.getThumbnailURL());
        existPost.setViewCount(post.getViewCount());
        repository.save(existPost);
        System.out.println("Updated post:" + existPost.toString());

    }

}
