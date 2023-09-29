package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        int indent = page * limit - 1;
        return posts.subList(indent - (limit - 1), indent);
    }

    @GetMapping("posts/{slug}")
    public Optional<Post> show(@PathVariable String slug) {
        return posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{slug}")
    public Post update(@PathVariable String slug, @RequestBody Post data) {
        var maybePost = posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst();
        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setSlug(data.getSlug());
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
        }
        return data;
    }

    @DeleteMapping("/posts/{slug}")
    public void delete(@PathVariable String slug) {
        posts.removeIf(p -> p.getSlug().equals(slug));
    }
    // END
}
