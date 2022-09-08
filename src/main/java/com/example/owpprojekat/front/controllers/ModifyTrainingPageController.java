package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.TrainingDto;
import com.example.owpprojekat.api.dto.TrainingTypeDto;
import com.example.owpprojekat.api.dto.UserDto;
import com.example.owpprojekat.api.enums.Role;
import com.example.owpprojekat.api.models.TrainingToType;
import com.example.owpprojekat.api.models.TrainingType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ModifyTrainingPageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    private Long id;

    @GetMapping("/training/modify")
    public String addTraining(Model model, HttpSession session) {
        try {
            UserDto.Get user = (UserDto.Get)session.getAttribute("user");
            if (user.getRole() != Role.ADMIN) {
                return "redirect:/";
            }
        } catch (Exception e) {
            return "redirect:/";
        }

        TrainingDto.Get training;
        List<TrainingTypeDto.Get> types = new ArrayList<>();
        try {
            id = Long.valueOf(request.getParameter("id"));
            training = client.getForObject("http://localhost:8080/api/training?id=" + id, TrainingDto.Get.class);
            if (training == null) {
                id = null;
            }
        } catch (Exception e) {
            training = null;
            id = null;
        }

        ObjectMapper mapper = new ObjectMapper();
        types = mapper.convertValue(client.getForObject("http://localhost:8080/api/training/types", types.getClass()), new TypeReference<List<TrainingTypeDto.Get>>() {
        });


        TrainingDto.Add data = new TrainingDto.Add();
        if (training != null) {
            data.setName(training.getName());
            data.setTrainers(training.getTrainers());
            data.setDescription(training.getDescription());
            data.setPic(training.getPic());
            data.setPrice(training.getPrice());
            data.setGroup(training.isGroup());
            data.setDifficulty(training.getDifficulty());
            data.setDuration(training.getDuration());

            List<Long> trainingTypes = new ArrayList<>();
            for (int i = 0; i < types.size(); i++) {
                if (training.getTypes().contains(types.get(i).getName())) {
                    trainingTypes.add(types.get(i).getId());
               }
            }
            data.setTypes(trainingTypes);

        } else {
            data.setTypes(new ArrayList<>());
            data.setPrice(100);
            data.setDuration(30);
        }
        model.addAttribute("data", data);
        model.addAttribute("types", types);
        return "modifyTraining";
    }

    @PostMapping("/training/modify")
    public String postAddTraining(@ModelAttribute TrainingDto.Add data, Model model) {
        model.addAttribute("data", data);
        ResponseEntity<TrainingDto.Get> response;
        if (id != null) {
            response = client.exchange("http://localhost:8080/api/training?id=" + id, HttpMethod.PUT , new HttpEntity<>(data), TrainingDto.Get.class);
        } else {
            response = client.exchange("http://localhost:8080/api/training", HttpMethod.POST, new HttpEntity<>(data), TrainingDto.Get.class);
        }

        //TODO obavestiti korisnika o uspelim/neuspleim dodavanjima/izmenama
        if (!response.hasBody()) {
            return "redirect:/training/modify?id=" + id;
        }
        return "redirect:/";
    }


}
