package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AppTest {
   @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 2));

        assertThat(App.take(numbers1, 2))
                .isEqualTo(expected1);

        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(7, 3, 10));

        assertThat(App.take(numbers2, 8))
                .isEqualTo(expected2);

        List<Integer> numbers3 = new ArrayList<>(List.of(1));
        List<Integer> expected3 = new ArrayList<>(List.of(1));

        assertThat(App.take(numbers3, 1))
                .isEqualTo(expected3);

        List<Integer> numbers4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected4 = new ArrayList<>(List.of());

        assertThat(App.take(numbers4, 0))
                .isEqualTo(expected4);
        
        List<Integer> numbers5 = new ArrayList<>(List.of());
        List<Integer> expected5 = new ArrayList<>(List.of());

        assertThat(App.take(numbers5, 5))
                .isEqualTo(expected5);
        // END
    }
}
