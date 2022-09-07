package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.*;
import com.example.owpprojekat.api.enums.Role;
import com.example.owpprojekat.api.models.LoyaltyCard;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                session.setAttribute("reports", null);
                return "redirect:/";
            }
        } catch (Exception e) {
            session.setAttribute("reports", null);
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

        List<CommentDto.Get> comments = new ArrayList<>();
        comments = client.getForObject("http://localhost:8080/api/comments", comments.getClass());
        model.addAttribute("comments", comments);

        model.addAttribute("reportData", new ReportDto.Request());
        if (session.getAttribute("reports") != null) {
            List<ReportDto.Get> reports = (List<ReportDto.Get>) session.getAttribute("reports");
            session.setAttribute("reports", null);
            model.addAttribute("reports", reports);
        }


        return "admin";
    }

    @PostMapping("/admin/request/accept")
    @ResponseStatus(value = HttpStatus.OK)
    public void acceptRequest(@RequestParam("id") String id) {
        ResponseEntity<LoyaltyCardDto.Get> response = client.exchange("http://localhost:8080/api/card/request/accept?id=" + id, HttpMethod.POST, new HttpEntity<>(null), LoyaltyCardDto.Get.class);
    }

    @PostMapping("/admin/request/decline")
    @ResponseStatus(value = HttpStatus.OK)
    public void declineRequest(@RequestParam("id") String id) {
        ResponseEntity<LoyaltyCardDto.Get> response = client.exchange("http://localhost:8080/api/card/request/decline?id=" + id, HttpMethod.DELETE, new HttpEntity<>(null), LoyaltyCardDto.Get.class);
    }

    @PostMapping(value = "/admin/hall/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteHall(@RequestParam("id") String id) {
        ResponseEntity<HallDto.Get> response = client.exchange("http://localhost:8080/api/hall?id=" + id, HttpMethod.DELETE, new HttpEntity<>(null), HallDto.Get.class);
    }

    @PostMapping(value = "/admin/comment/accept")
    @ResponseStatus(value = HttpStatus.OK)
    public void acceptComment(@RequestParam("id") String id) {
        ResponseEntity<CommentDto.Get> response = client.exchange("http://localhost:8080/api/comment/accept?id=" + id, HttpMethod.PUT, new HttpEntity<>(null), CommentDto.Get.class);
    }

    @PostMapping(value = "/admin/comment/decline")
    @ResponseStatus(value = HttpStatus.OK)
    public void declineComment(@RequestParam("id") String id) {
        ResponseEntity<CommentDto.Get> response = client.exchange("http://localhost:8080/api/comment/decline?id=" + id, HttpMethod.PUT, new HttpEntity<>(null), CommentDto.Get.class);
    }

    @PostMapping(value = "/admin/report")
    public String generateReport(@ModelAttribute ReportDto.Request data, Model model, HttpSession session) {
        model.addAttribute("reportData", data);
        List<ReportDto.Get> reports = new ArrayList<>();
        reports = client.postForObject("http://localhost:8080/api/report", new ReportDto.Request(data.getDateFrom(), data.getDateTo()), reports.getClass());
        session.setAttribute("reports", reports);
        return "redirect:/admin";
    }

}
