package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class InMemoryKV implements KeyValueStorage {
    private Map<String, String> storage;

    public InMemoryKV(Map<String, String> keyValue) {
        this.storage = new HashMap<>(keyValue);
    }

    @Override
    public void set(String key, String value) {
        this.storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.storage.get(key) == null ? defaultValue : this.storage.get(key);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(this.storage);
    }
}
// END
