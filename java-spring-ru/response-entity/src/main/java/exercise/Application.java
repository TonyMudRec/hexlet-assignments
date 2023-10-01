package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

import static org.springframework.boot.web.servlet.server.Session.SessionTrackingMode.URL;

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
    public ResponseEntity<List<Post>> index() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var post = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        return ResponseEntity.of(post);
    }

    @RequestMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.created(URI.create("/posts"))
                .header("Content-Type", "application/json")
                .body(post);
    }

    @RequestMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var MaybePost = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (MaybePost.isPresent()) {
            var post = MaybePost.get();
            post.setTitle(data.getTitle());
            post.setId(id);
            post.setBody(data.getBody());
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(post);
        }
        return ResponseEntity.noContent()
                .build();
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
