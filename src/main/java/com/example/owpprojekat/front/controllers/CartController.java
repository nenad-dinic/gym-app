package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.front.data.Cart;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CartController {

    @PostMapping(value = "/cart/add",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Long addToCart(@RequestParam("id") String id, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }
        Cart cart = (Cart) session.getAttribute("cart");
        cart.addToCart(Long.parseLong(id));
        session.setAttribute("cart", cart);
        return Long.parseLong(id);
    }

    @PostMapping(value = "/cart/remove",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public Long removeFromCart(@RequestParam("id") String id, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeFromCart(Long.parseLong(id));
        return Long.parseLong(id);
    }
}
