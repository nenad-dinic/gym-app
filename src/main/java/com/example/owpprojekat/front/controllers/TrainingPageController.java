package com.example.owpprojekat.front.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class TrainingPageController {

    @Autowired
    private HttpServletRequest servletContext;

    @GetMapping("/training")
    public String training(Model model) {
        model.addAttribute("query", servletContext.getParameter("id"));
        return "training";
    }
}
