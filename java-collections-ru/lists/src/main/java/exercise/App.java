package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static void main(String[] args) {
        System.out.println(App.scrabble("rkqodlw", "world")); // true
        System.out.println(App.scrabble("ajv", "java")); // false
        System.out.println(App.scrabble("avjafff", "JaVa")); // true
        System.out.println(App.scrabble("", "hexlet")); // false
    }

    public static boolean scrabble(String letters, String word) {
        letters = letters.toLowerCase();
        word = word.toLowerCase();
        List<String> wordList = new ArrayList<>(Arrays.asList(word.split("")));
        List<String> letterList = new ArrayList<>(Arrays.asList(letters.split("")));
        for (String wordLetter : wordList) {
            if (letterList.contains(wordLetter)) {
                letterList.remove(wordLetter);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
