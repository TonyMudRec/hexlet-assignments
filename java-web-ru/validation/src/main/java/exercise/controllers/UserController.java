package exercise.controllers;

import io.javalin.http.Handler;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.javalin.validation.Validator;
import io.javalin.validation.ValidationError;
import io.javalin.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;
import io.javalin.http.Context;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    private static void removeFlashMessage(Context ctx) {
        ctx.sessionAttribute("flash", null);
    }

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
        removeFlashMessage(ctx);
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
                .id.equalTo(id)
                .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        Validator<String> firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(value -> !value.isEmpty(), "field firstName name cannot be empty");

        Validator<String> lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(value -> !value.isEmpty(), "field lastName cannot be empty");

        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(value -> EmailValidator.getInstance().isValid(value), "field email is invalid");

        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(value -> value.length() > 3, "password must be longer then 4 characters")
                .check(StringUtils::isNumeric, "password must contain only numbers");

        Map<String, List<ValidationError<?>>> errors = JavalinValidation.collectErrors(
                firstNameValidator,
                lastNameValidator,
                emailValidator,
                passwordValidator
        );

        User user = new User(firstName, lastName, email, password);

        if (!errors.isEmpty()) {
            ctx.status(422);
            ctx.attribute("errors", errors);
            ctx.attribute("user", user);
            ctx.render("/users/new.html");
            return;
        }
        ctx.attribute("flash", "user created");
        user.save();
        ctx.redirect("/users");
        // END
    };
}
