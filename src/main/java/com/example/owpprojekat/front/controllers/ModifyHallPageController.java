package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.HallDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ModifyHallPageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    private Long id;

    @GetMapping("/hall")
    public String hall(Model model) {
        HallDto.Get hall;
        try {
            id = Long.parseLong(request.getParameter("id"));
            hall = client.getForObject("http://localhost:8080/api/hall?id=" + id, HallDto.Get.class);
            if (hall == null) {
                id = null;
            }
        } catch (Exception e) {
            hall = null;
            id = null;
        }

        HallDto.Add data = new HallDto.Add();
        if (hall != null) {
            data.setHallTag(hall.getHallTag());
            data.setCapacity(hall.getCapacity());
        }

        model.addAttribute("data", data);
        model.addAttribute("hall", hall);
        return "modifyHall";
    }

    @PostMapping("/hall")
    public String postHall(@ModelAttribute HallDto.Add data, Model model) {
        model.addAttribute("data", data);
        ResponseEntity<HallDto.Get> response;
        if (id != null) {
            response = client.exchange("http://localhost:8080/api/hall?id=" + id, HttpMethod.PUT, new HttpEntity<>(data), HallDto.Get.class);
        } else {
            response = client.exchange("http://localhost:8080/api/hall", HttpMethod.POST, new HttpEntity<>(data), HallDto.Get.class);
        }

        //TODO obavestiti korisnika o uspelim/neuspleim dodavanjima/izmenama
        if (!response.hasBody()) {
            return "redirect:/hall?id=" + id;
        }
        return "redirect:/admin";
    }
}
