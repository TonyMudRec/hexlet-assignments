package exercise.controller;

import java.util.Collections;
import java.util.List;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;

public class PostsController {
    private static final int PER = 5;

    // BEGIN
    public static void index(Context ctx) {
        int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int firstPageIndex = (pageNumber - 1) * PER;
        List<Post> posts = PostRepository.getEntities().subList(firstPageIndex, firstPageIndex + PER);
        PostsPage page = new PostsPage(posts, pageNumber);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        long id = Long.parseLong(ctx.pathParam("id"));
        Post post = PostRepository.find(id);

        if (post == null) {
            ctx.status(404);
            ctx.result("Page not found");
            return;
        }

        PostPage page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}
