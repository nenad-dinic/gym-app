package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.ReservationDto;
import com.example.owpprojekat.api.dto.ScheduleDto;
import com.example.owpprojekat.api.dto.TrainingDto;
import com.example.owpprojekat.api.dto.UserDto;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationPageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    Long id;

    @GetMapping("/reservation")
    public String reservation(Model model, HttpSession session) {
        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (Exception e) {
            return "redirect:/";
        }

        ReservationDto.Get reservation = client.getForObject("http://localhost:8080/api/reservation?id=" + id, ReservationDto.Get.class);
        if (reservation == null) {
            return "redirect:/";
        }

        try {
            UserDto.Get user = (UserDto.Get)session.getAttribute("user");
            if (user.getId() != reservation.getUserId()) {
                return "redirect:/";
            }
        } catch (Exception e) {
            return "redirect:/";
        }

        List<ScheduleDto.Get> schedule = new ArrayList<>();
        schedule = client.getForObject("http://localhost:8080/api/schedule/reservation?id=" + id, schedule.getClass());

        model.addAttribute("reservation", reservation);
        model.addAttribute("schedule", schedule);
        return "reservation";
    }

    @PostMapping("/reservation/schedule")
    @ResponseStatus(value = HttpStatus.OK)
    public void cancelReservation(@RequestParam("sId") String sId, @RequestParam("rId") String rId) {
        ResponseEntity<ScheduleDto.Get> response = client.exchange("http://localhost:8080/api/reservation?scheduleId=" + sId + "&reservationId=" + rId, HttpMethod.DELETE, new HttpEntity<>(null), ScheduleDto.Get.class);
    }
}
