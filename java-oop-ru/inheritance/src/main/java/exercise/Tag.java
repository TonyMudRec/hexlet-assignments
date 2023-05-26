package exercise;

import java.util.Map;

// BEGIN
class Tag {
    protected final String name;
    protected final Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : attributes.keySet()) {
            sb
                    .append(" ")
                    .append(key)
                    .append("=\"")
                    .append(attributes.get(key))
                    .append("\"");
        }
        return sb.toString();
    }
}
// END
