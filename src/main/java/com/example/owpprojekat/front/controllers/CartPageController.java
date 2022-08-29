package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.CartItemDto;
import com.example.owpprojekat.api.dto.ReservationDto;
import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.front.data.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartPageController {

    RestTemplate client = new RestTemplate();

    @GetMapping(value = "/cart")
    public String cart(Model model, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItemDto.Get> items = new ArrayList<>();
        items = client.postForObject("http://localhost:8080/api/cart", new CartItemDto.CartItems(cart.getItems()), items.getClass());
        model.addAttribute("items", items);
        model.addAttribute("data", new ReservationDto.Add());
        return "shoppingCart";
    }

    @PostMapping(value = "/cart/remove",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeFromCart(@RequestParam("id") String id, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeFromCart(Long.parseLong(id));
    }

    @PostMapping(value = "/cart")
    public String makeReservation(@ModelAttribute ReservationDto.Add data, Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        UserDto.Get user = (UserDto.Get) session.getAttribute("user");
        data.setUserId(user.getId());
        data.setSchedules(cart.getItems());
        ReservationDto.Get result = client.postForObject("http://localhost:8080/api/reservation", data, ReservationDto.Get.class);
        if (result != null) {
            cart.clear();
            session.setAttribute("cart", cart);
            //TODO redirect to profile
            return "redirect:/cart";
        }

        return "redirect:/cart";
    }
}
