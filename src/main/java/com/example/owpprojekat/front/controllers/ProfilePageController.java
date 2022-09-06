package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.LoyaltyCardRequestDto;
import com.example.owpprojekat.api.dto.ReservationDto;
import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.api.dto.WishlistDto;
import com.example.owpprojekat.api.models.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfilePageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    Long id;

    @GetMapping("/profile")
    public String profile(Model model) {
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            return "redirect:/";
        }

        UserDto.Get user = client.getForObject("http://localhost:8080/api/user?id=" + id, UserDto.Get.class);
        if (user == null) {
            return "redirect:/";
        }

        List<WishlistDto.Get> wishlist = new ArrayList<>();
        wishlist = client.getForObject("http://localhost:8080/api/user/wishlist?id=" + id, wishlist.getClass());

        List<ReservationDto.Get> reservations = new ArrayList<>();
        reservations = client.getForObject("http://localhost:8080/api/reservation/user?id=" + id, reservations.getClass());

        model.addAttribute("reservations", reservations);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("data", user);
        return "profile";
    }

    @PostMapping("/profile/requestCard")
    public String requestCard(@RequestParam("id") String id) {
        LoyaltyCardRequestDto.Add data = new LoyaltyCardRequestDto.Add(Long.parseLong(id));
        LoyaltyCardRequestDto.Get response = client.postForObject("http://localhost:8080/api/card/request", data, LoyaltyCardRequestDto.Get.class);
        if (response == null) {
            return "redirect:/profile?id=" + id;
        }
        return "redirect:/profile?id=" + id;
    }

    @PostMapping("/profile/block")
    public String block(@RequestParam("id") String id) {
        ResponseEntity<UserDto.Get> response = client.exchange("http://localhost:8080/api/user/block?id=" + id, HttpMethod.PUT, new HttpEntity<>(null), UserDto.Get.class);
        if (!response.hasBody()) {
            return "redirect:/profile?id=" + id;
        }
        return "redirect:/profile?id=" + id;
        //TODO ne refresh stranicu ispitati zasto
    }

    @PostMapping("profile/admin")
    public String admin(@RequestParam("id") String id) {
        ResponseEntity<UserDto.Get> response = client.exchange("http://localhost:8080/api/user/admin?id=" + id, HttpMethod.PUT, new HttpEntity<>(null), UserDto.Get.class);
        if (!response.hasBody()) {
            return "redirect:/profile?id=" + id;
        }
        return "redirect:/profile?id=" + id;
        //TODO ne refresh stranicu ispitati zasto
    }

    @PostMapping("profile/wishlist")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeFromWishlist(@RequestParam("id") String id) {
        ResponseEntity<WishlistDto.Get> response = client.exchange("http://localhost:8080/api/user/wishlist?id=" + id, HttpMethod.DELETE, new HttpEntity<>(null), WishlistDto.Get.class);
    }

}
