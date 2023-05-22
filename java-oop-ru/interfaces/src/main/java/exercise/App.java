package exercise;

import java.util.Comparator;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int n) {
        return homes.stream()
                .sorted(new HomeComparator())
                .map(Object::toString)
                .toList();
    }
}

class HomeComparator implements Comparator<Home> {
    @Override
    public int compare(Home home, Home t1) {
        return home.compareTo(t1);
    }
}
// END
