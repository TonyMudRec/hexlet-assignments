package exercise;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class Sorter {

    public static void main(String[] args) {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> men = Sorter.takeOldestMans(users);
        System.out.println(men); // ["John Smith", "Andrey Petrov", "Vladimir Nikolaev"]
    }
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted((user1, user2) -> {
                    String birthdayO1Str = StringUtils.replaceChars(user1.get("birthday"),
                            "-", "");
                    String birthdayO2Str = StringUtils.replaceChars(user2.get("birthday"),
                            "-", "");
                    int birthdayO1 = Integer.parseInt(birthdayO1Str);
                    int birthdayO2 = Integer.parseInt(birthdayO2Str);
                    return Integer.compare(birthdayO1, birthdayO2);
                })
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }
}
// END
