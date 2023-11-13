package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Post> index() {
        return postRepository.findAll().stream().toList();
    }

    @GetMapping("/{id}")
    public Post show(@PathVariable String id) {
        var maybePost = postRepository.findById(Long.parseLong(id));
        return maybePost.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }

    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.)
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = postRepository.findById(Long.parseLong(id));
        if (maybePost.isPresent()) {
            Post post = maybePost.get();
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
            post.setId(Long.parseLong(id));
            return post;
        }
        return data;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        postRepository.deleteById(Long.parseLong(id));
        commentRepository.deleteByPostId(Long.parseLong(id));
    }
}
// END