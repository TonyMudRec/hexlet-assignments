package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<PostDTO> index() {
        List<PostDTO> posts = new ArrayList<>();
        postRepository.findAll()
                .forEach(p -> {
                    var postDTO = new PostDTO();
                    postDTO.setId(p.getId());
                    postDTO.setBody(p.getBody());
                    postDTO.setTitle(p.getTitle());
                    posts.add(postDTO);
                });
        return posts;
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable String id) {
        PostDTO postDTO = new PostDTO();
        var post = postRepository
                .findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        List<CommentDTO> comments = new ArrayList<>();
        commentRepository.findByPostId(Long.parseLong(id)).stream().forEach(c -> {
            var commentDTO = new CommentDTO();
            commentDTO.setId(c.getId());
            commentDTO.setBody(c.getBody());
            comments.add(commentDTO);
        });
        postDTO.setId(post.getId());
        postDTO.setComments(comments);
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
}
// END
