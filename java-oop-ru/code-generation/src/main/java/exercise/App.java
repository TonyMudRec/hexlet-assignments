package exercise;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) {
        try {
            byte[] bytes = car.serialize().getBytes();
            Files.write(path, bytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    @SneakyThrows
    public static Car extract(Path path) {
        return Car.unserialize(Files.readString(path));
    }
}
// END
