package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// BEGIN
class App {
    public static void main(String[] args) {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        System.out.println(Arrays.deepToString(image));
        String[][] enlargedImage = App.enlargeArrayImage(image);
        System.out.println(Arrays.deepToString(enlargedImage));
    }

    public static String[][] enlargeArrayImage(String[][] image) {
        List<String[]> result = new ArrayList<>();
        List<String> pixelLine = new ArrayList<>();
        Arrays.stream(image)
                .flatMap(Arrays::stream)
                .forEach(pixel -> {
                    pixelLine.add(pixel);
                    pixelLine.add(pixel);
                    if (pixelLine.size() == image.length * 2) {
                        result.add(pixelLine.toArray(String[]::new));
                        result.add(pixelLine.toArray(String[]::new));
                        pixelLine.clear();
                    }
                });
        return result.toArray(String[][]::new);
    }
}
// END
