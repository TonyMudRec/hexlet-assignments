package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String addItemPath() {
        return "/cart-items";
    }

    public static String clearCartPath() {
        return "/cart-items/clean";
    }
}
