package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.HallDto;
import com.example.owpprojekat.api.dto.LoyaltyCardDto;
import com.example.owpprojekat.api.dto.LoyaltyCardRequestDto;
import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.api.enums.Role;
import com.example.owpprojekat.api.models.LoyaltyCard;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPageController {

    RestTemplate client = new RestTemplate();

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session) {
        //TODO dodati ovaj try gde ne zelimo pristup
        try {
            UserDto.Get user = (UserDto.Get)session.getAttribute("user");
            if (user.getRole() != Role.ADMIN) {
                return "redirect:/";
            }
        } catch (Exception e) {
            return "redirect:/";
        }


        List<HallDto.Get> halls = new ArrayList<>();
        halls = client.getForObject("http://localhost:8080/api/halls", halls.getClass());
        model.addAttribute("halls", halls);

        List<LoyaltyCardRequestDto.Get> requests = new ArrayList<>();
        requests = client.getForObject("http://localhost:8080/api/card/request", requests.getClass());
        model.addAttribute("requests", requests);

        List<UserDto.Get> users = new ArrayList<>();
        users = client.getForObject("http://localhost:8080/api/users", users.getClass());
        model.addAttribute("users", users);

        return "admin";
    }

    @PostMapping("/admin/request/accept")
    public String acceptRequest(@RequestParam("id") String id) {
        ResponseEntity<LoyaltyCardDto.Get> response = client.exchange("http://localhost:8080/api/card/request/accept?id=" + id, HttpMethod.POST, new HttpEntity<>(null), LoyaltyCardDto.Get.class);
        if (!response.hasBody()) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }//TODO ne refresh stranicu ispitati zasto

    @PostMapping("/admin/request/decline")
    public String declineRequest(@RequestParam("id") String id) {
        ResponseEntity<LoyaltyCardDto.Get> response = client.exchange("http://localhost:8080/api/card/request/decline?id=" + id, HttpMethod.DELETE, new HttpEntity<>(null), LoyaltyCardDto.Get.class);
        if (!response.hasBody()) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }//TODO ne refresh stranicu ispitati zasto
}
