package exercise.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.dto.CartPage;
import exercise.util.NamedRoutes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.model.CartItem;
import org.eclipse.jetty.util.UrlEncoded;

import io.javalin.http.Context;


public class CartsController {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void index(Context ctx) throws Exception {
        var cookie = ctx.cookie("cart") == null ? "{}" : ctx.cookie("cart");

        Map<String, CartItem> cart = mapper.readValue(
            UrlEncoded.decodeString(cookie),
            new TypeReference<Map<String, CartItem>>() { }
        );

        var page = new CartPage(cart);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void addToCart(Context ctx) throws Exception {
        CartItem item;
        String cookie = ctx.cookie("cart") == null ? "{}" : ctx.cookie("cart");
        String name = ctx.formParam("name");
        Map<String, CartItem> cart = mapper.readValue(
                UrlEncoded.decodeString(cookie),
                new TypeReference<Map<String, CartItem>>() {}
        );

        if (!cart.containsKey(name)) {
            item = new CartItem(name, 1);
        } else {
            cookie = ctx.cookie("cart");
            item = cart.get(name);
            item.increaseCount();
        }

        cart.put(name, item);
        ctx.cookie("cart", serializeCookie(cart));
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void cleanCart(Context ctx) throws Exception {
        String cookie = ctx.cookie("cart");

        Map<String, CartItem> cart = mapper.readValue(
                UrlEncoded.decodeString(cookie),
                new TypeReference<Map<String, CartItem>>() { }
        );
        cart.clear();
        ctx.cookie("cart", serializeCookie(cart));
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static String serializeCookie(Map<String, CartItem> cart) throws JsonProcessingException {
        String json = mapper.writeValueAsString(cart);
        return UrlEncoded.encodeString(json);
    }
    // END
}
