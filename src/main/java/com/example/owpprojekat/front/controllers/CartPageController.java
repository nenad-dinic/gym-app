package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.front.data.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<ScheduleDto.Get> schedules = new ArrayList<>();
        for (Long id : cart.getItems()) {
            schedules.add(client.getForObject("http://localhost:8080/api/schedule?id=" + id, ScheduleDto.Get.class));
        }
        model.addAttribute("schedules", schedules);
        return "shoppingCart";
    }
}
