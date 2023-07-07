package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("users");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testValidData() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Daniel")
                .field("lastName", "Radcliffe")
                .field("email", "photter@griffindor.com")
                .field("password", "123456")
                .asString();

        assertThat(response.getStatus()).isEqualTo(302);

        User actualUser = new QUser()
                .firstName.equalTo("Daniel")
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getEmail()).isEqualTo("photter@griffindor.com");
    }

    @Test
    void testInvalidData() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Daniel")
                .field("lastName", "Radcliffe")
                .field("email", "photter@griffindor.com")
                .field("password", "123")
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User actualUser = new QUser()
                .email.equalTo("photter@griffindor.com")
                .findOne();

        assertThat(actualUser).isNull();
        String body = response.getBody();
        assertThat(body).contains("Daniel");
        assertThat(body).contains("Пароль должен содержать не менее 4 символов");
    }

    @AfterAll
    static  void afterAll() {
        app.stop();
    }
    // END
}
