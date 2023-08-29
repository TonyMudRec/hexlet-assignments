package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }

    public static String show(String id) {
        return "/posts/" + id;
    }

    public static String show(Long id) {
        return show(String.valueOf(id));
    }
    // END
}
