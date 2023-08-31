package exercise.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class CartItem {
    private String name;
    private int count;

    public CartItem(@JsonProperty("name") String name, @JsonProperty("count") int count) {
        this.name = name;
        this.count = count;
    }

    public void increaseCount() {
        count++;
    }
}
