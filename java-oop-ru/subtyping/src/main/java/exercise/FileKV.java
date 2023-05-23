package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private String filepath;
    private Map<String, String> storage;

    public FileKV(String filepath, Map<String, String> storage) {
        this.filepath = filepath;
        this.storage = new HashMap<>(storage);
        Utils.writeFile(filepath, Utils.serialize(storage));
    }

    @Override
    public void set(String key, String value) {
        this.storage.put(key, value);
        Utils.writeFile(filepath, Utils.serialize(this.storage));
    }

    @Override
    public void unset(String key) {
        this.storage.remove(key);
        Utils.writeFile(filepath, Utils.serialize(this.storage));
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
