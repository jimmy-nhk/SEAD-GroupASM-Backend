package SEAD.POST.Controller;

import SEAD.POST.Model.Post;
import SEAD.POST.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@RequestMapping
//@Slf4j
public class PostController {
    @Autowired
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }


    //POST mapping

    @PostMapping(path = "/post")
    public Post addPost(@RequestBody Post post) {

        return service.addPost(post);
    }
    @PostMapping(path = "/posts")
    public List<Post> addPosts(@RequestBody List<Post> postList) {
        return service.addPosts(postList);
    }

    //GET mapping
    @GetMapping(path = "/posts")
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }
    @GetMapping(path = "/post/{id}")
    public Post getPost(@PathVariable Long id) {
        return service.getPost(id);
    }

    //PUT mapping
    @PutMapping(path = "/post")
    public void updPost(@RequestBody Post post) {
         service.updatePost(post);
    }

    @PutMapping(path = "/posts")
    public List<Post> updPosts(@RequestBody List<Post> postList) {
        return service.updPosts(postList);
    }
    //DELETE mapping
    @DeleteMapping(path = "/post")
    public void delPost(@RequestBody Post post) {
        service.updatePost(post);
    }
    @DeleteMapping(path = "/post/{id}")
    public void delPostByID(@PathVariable Long id) {
        service.delPost(id);
    }
}
