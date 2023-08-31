package exercise.dto;

import java.util.Map;
import exercise.model.CartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartPage {
    private Map<String, CartItem> cart;
}
