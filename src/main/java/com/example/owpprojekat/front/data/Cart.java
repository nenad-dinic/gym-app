package com.example.owpprojekat.front.data;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Long> cart = new ArrayList<>();

    public void addToCart(Long id) {
        if (!cart.contains(id)) {
            cart.add(id);
        }
    }

    public void removeFromCart(Long id) {
        cart.remove(id);
    }

    public List<Long> getItems() {
        return cart;
    }
}
