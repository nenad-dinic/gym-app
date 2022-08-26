package com.example.owpprojekat.front.data;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<Long> cart = new ArrayList<>();

    public static void addToCart(Long id) {
        if (!cart.contains(id)) {
            cart.add(id);
        }
    }

    public static void removeFromCart(Long id) {
        cart.remove(id);
    }

    public static List<Long> getItems() {
        return cart;
    }
}
