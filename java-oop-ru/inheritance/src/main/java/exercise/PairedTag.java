package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
class PairedTag extends Tag {
    private final String body;
    private final List<Tag> child;

    public PairedTag(String name, Map<String, String> attributes, String body,  List<Tag> child) {
        super(name, attributes);
        this.body = body;
        this.child = child;
    }
    public String getChildList() {
        StringBuilder sb = new StringBuilder();
        for (Tag tag : child) {
            sb.append(tag.toString());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return  "<" + name + super.toString() + ">" + body + getChildList() + "</" + name + ">";
    }
}
// END
