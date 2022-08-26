package com.example.owpprojekat.api.controllers;

import com.example.owpprojekat.front.data.Cart;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @PostMapping(value = "/api/cart/add",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Long addToCart(@RequestParam("id") String id) {
        Cart.addToCart(Long.parseLong(id));
        return Long.parseLong(id);
    }
}
