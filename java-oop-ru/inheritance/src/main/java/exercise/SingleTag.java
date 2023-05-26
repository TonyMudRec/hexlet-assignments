package exercise;

import java.util.Map;

// BEGIN
class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }
    @Override
    public String toString() {
        return  "<" + name + super.toString() + ">";
    }
}
// END
