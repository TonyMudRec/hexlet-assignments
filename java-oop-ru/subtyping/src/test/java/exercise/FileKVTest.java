package exercise;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void initialTest() {
        KeyValueStorage storage = new FileKV(String.valueOf(filepath), Map.of("key1", "value1"));
        storage.set("key2", "value2");

        assertThat(Utils.unserialize(Utils.readFile(String.valueOf(filepath)))).isEqualTo(storage.toMap());
    }

    @Test
    void getTest() {
        KeyValueStorage storage = new FileKV(String.valueOf(filepath), Map.of("key", "value"));

        storage.set("key2", "value2");
        assertThat(storage.get("key2", "")).isEqualTo("value2");
        assertThat(storage.get("key3", "default")).isEqualTo("default");
        assertThat(storage.get("key", "")).isEqualTo("value");
        storage.set("key2", "value3");
        assertThat(storage.get("key2", "")).isEqualTo("value3");
    }

    @Test
    void unsetTest() {
        KeyValueStorage storage = new FileKV(String.valueOf(filepath), Map.of("key1", "value1"));
        storage.set("key2", "value2");
        String context1 = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        String context2 = "{\"key2\":\"value2\"}";
        Map<String, String> expected = Map.of("key2", "value2");

        assertThat(Utils.readFile(String.valueOf(filepath))).isEqualTo(context1);
        storage.unset("key1");
        assertThat(storage.toMap()).isEqualTo(expected);
//        assertThat(Utils.readFile(String.valueOf(filepath))).isEqualTo(context2);
    }
    // END
}