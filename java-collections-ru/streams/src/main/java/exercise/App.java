package exercise;

import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static void main(String[] args) {
        String[] emails = {
                "info@gmail.com",
                "info@yandex.ru",
                "mk@host.com",
                "support@hexlet.io",
                "info@hotmail.com",
                "support.yandex.ru@host.com"
        };

        List<String> emailsList = Arrays.asList(emails);
        System.out.println(App.getCountOfFreeEmails(emailsList)); // 3
    }

    public static int getCountOfFreeEmails(List<String> emailsList) {
        return (int) emailsList.stream()
                .filter(StringUtils::isNotBlank)
                .filter(email -> email.endsWith("gmail.com") || email.endsWith("yandex.ru")
                        || email.endsWith("hotmail.com"))
                .count();
    }
}
// END
