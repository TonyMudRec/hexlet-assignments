package exercise.util;

import java.util.Random;
import java.util.Locale;
import com.github.javafaker.Faker;
import java.util.List;
import java.util.ArrayList;
import exercise.model.Post;

public class Generator {
    private static final int ITEMS_COUNT = 4;
    private static final Random RANDOM = new Random(123);

    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        Faker faker = new Faker(new Locale("en"), RANDOM);

        for (int i = 0; i < ITEMS_COUNT; i++) {
            var name = faker.book().title();
            var body = faker.lorem().sentence();
            var id = (long) i + 1;
            var post = new Post(id, name, body);
            posts.add(post);
        }

        return posts;
    }

}
