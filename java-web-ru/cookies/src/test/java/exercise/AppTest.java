package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void testCreatePost() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/")
            .asString();

        String body = response.getBody();
        assertEquals(200, response.getStatus());

        assertThat(body, containsString("Корзина пуста"));

        HttpResponse responsePost = Unirest
            .post(baseUrl + "/cart-items")
            .field("id", "1")
            .field("name", "One")
            .asEmpty();

        assertEquals(302, responsePost.getStatus());
        assertEquals("/", responsePost.getHeaders().getFirst("Location"));

        response = Unirest.get(baseUrl + "/").asString();
        body = response.getBody();
        assertThat(body, is(not(containsString("Корзина пуста"))));
        assertThat(body, containsString("One"));
        assertThat(body, containsString("1"));

        responsePost = Unirest
            .post(baseUrl + "/cart-items")
            .field("id", "1")
            .field("name", "One")
            .asEmpty();
        response = Unirest.get(baseUrl + "/").asString();
        body = response.getBody();
        assertThat(body, containsString("One"));
        assertThat(body, containsString("2"));

        responsePost = Unirest
            .post(baseUrl + "/cart-items")
            .field("id", "2")
            .field("name", "Two")
            .asEmpty();
        response = Unirest.get(baseUrl + "/").asString();
        body = response.getBody();
        assertThat(body, containsString("One"));
        assertThat(body, containsString("2"));
        assertThat(body, containsString("Two"));
        assertThat(body, containsString("1"));

        responsePost = Unirest
            .post(baseUrl + "/cart-items/clean")
            .asEmpty();
        response = Unirest.get(baseUrl + "/").asString();
        body = response.getBody();
        assertThat(body, is(not(containsString("One :"))));
        assertThat(body, is(not(containsString("Two :"))));
        assertThat(body, containsString("Корзина пуста"));
    }
}
