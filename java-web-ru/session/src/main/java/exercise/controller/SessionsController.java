package exercise.controller;

import java.util.Collections;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.Generator;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        String name = ctx.sessionAttribute("UserName");
        MainPage page = new MainPage(name);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        LoginPage page = new LoginPage(null, null);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void loginUser(Context ctx) {
        String name = ctx.formParam("name");
        String password = encrypt(ctx.formParam("password"));

        User bdUser = Generator.getUsers().stream()
                .filter(u -> u.getName().equals(name))
                .findFirst().orElse(null);

        if (bdUser == null || !password.equals(bdUser.getPassword())) {
            LoginPage page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", Collections.singletonMap("page", page));
            return;
        }
        ctx.sessionAttribute("UserName", name);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void deleteSession(Context ctx) {
        ctx.sessionAttribute("UserName", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
