package exercise.dto.posts;

import exercise.dto.BasePage;
import exercise.model.Post;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class PostPage {
    private Post post;
}
