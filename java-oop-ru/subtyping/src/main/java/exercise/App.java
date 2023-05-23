package exercise;

import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Set<String> keys = storage.toMap().keySet();
        String value;
        for (String key : keys) {
            value = storage.toMap().get(key);
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
// END
