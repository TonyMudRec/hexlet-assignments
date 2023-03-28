package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static void main(String[] args) {
        String sentence = "the java is the best programming language java";
        Map wordsCount = App.getWordCount(sentence);
        String result = App.toString(wordsCount);
        System.out.println(result);
    }

    public static Map<String, Integer> getWordCount(String sentence) {
        String[] words = sentence.split(" ");
        Map<String, Integer> result = new HashMap<>();
        int tmp = 0;
        for (String word : words) {
            if (result.containsKey(word)) {
                tmp = result.get(word);
                result.put(word, tmp + 1);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }

    protected static <S, I> String toString(Map<S, I> sentence) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\n");
        for (Map.Entry<S, I> entry : sentence.entrySet()) {
            sb.append(String.format("  %s: %1d\n", entry.getKey(), entry.getValue()));
        }
        sb.append("}");
        return sb.toString();
    }
}
//END
