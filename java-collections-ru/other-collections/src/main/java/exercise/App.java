package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> mapA, Map<String, Object> mapB) {
        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keysMapA = new TreeSet<>(mapA.keySet());
        Set<String> keysMapB = new TreeSet<>(mapB.keySet());
        Set<String> union = new TreeSet<>(keysMapA);
        Set<String> intersection = new TreeSet<>(keysMapB);
        
        union.addAll(keysMapB);
        intersection.retainAll(keysMapA);
        union.forEach(i -> {
            String value = intersection.contains(i) ? "changed" : "null";
            if (value.equals("changed")) {
                value = mapA.get(i).equals(mapB.get(i)) ? "un".concat(value) : value;
            } else {
                value = mapA.containsKey(i) ? "deleted" : "added";
            }
            result.put(i, value);
        });
        return result;
    }
}
//END
