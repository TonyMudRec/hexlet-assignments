package exercise;

import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    @SneakyThrows
    public static Car unserialize(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Car.class);
    }
    // END
}
