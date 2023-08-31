package exercise;

import io.javalin.Javalin;
import exercise.controller.CartsController;
import exercise.util.NamedRoutes;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get(NamedRoutes.rootPath(), CartsController::index);
        // BEGIN
        app.post(NamedRoutes.addItemPath(), CartsController::addToCart);
        app.post(NamedRoutes.clearCartPath(), CartsController::cleanCart);
        // END



        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
