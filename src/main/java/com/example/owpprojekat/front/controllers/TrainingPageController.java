package com.example.owpprojekat.front.controllers;

import com.example.owpprojekat.api.dto.*;
import com.example.owpprojekat.front.data.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainingPageController {

    RestTemplate client = new RestTemplate();

    @Autowired
    private HttpServletRequest request;

    Long id;

    @GetMapping("/training")
    public String training(Model model) {

        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (Exception e) {
            return "redirect:/";
        }
        TrainingDto.Get training = client.getForObject("http://localhost:8080/api/training?id=" + id, TrainingDto.Get.class);
        if (training == null) {
            return "redirect:/";
        }
        List<ScheduleDto.Get> schedule = new ArrayList<>();
        schedule = client.getForObject("http://localhost:8080/api/schedule/training?id=" + id, schedule.getClass());
        List<CommentDto.Get> comments = new ArrayList<>();
        comments = client.getForObject("http://localhost:8080/api/comments/training?id=" + id, comments.getClass());

        CommentDto.Add commentData = new CommentDto.Add();
        commentData.setRating(3);

        model.addAttribute("comments", comments);
        model.addAttribute("commentData", commentData);
        model.addAttribute("schedule", schedule);
        model.addAttribute("data", training);
        return "training";
    }

    @PostMapping(value = "/cart/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCart(@RequestParam("id") String id, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }
        Cart cart = (Cart) session.getAttribute("cart");
        cart.addToCart(Long.parseLong(id));
        session.setAttribute("cart", cart);
    }

    @PostMapping(value = "/wishlist/add")
    @ResponseStatus(value = HttpStatus.OK)
    public String addToWishlist(@RequestParam("uId") String uId, @RequestParam("tId") String tId) {
        WishlistDto.Get response = client.postForObject("http://localhost:8080/api/user/wishlist", new WishlistDto.Add(Long.parseLong(uId), Long.parseLong(tId)), WishlistDto.Get.class);
        if (response != null) {
            return "redirect:/training?id=" + id;
        }
        return "redirect:/training?id=" + id;
    }

    @PostMapping(value = "/comment")
    public String postComment(@ModelAttribute CommentDto.Add commentData, Model model, HttpSession session) {
        commentData.setTrainingId(id);
        UserDto.Get user = (UserDto.Get) session.getAttribute("user");
        if (user != null) {
            commentData.setUserId(user.getId());
        }

        model.addAttribute("commentData", commentData);
        CommentDto.Get response = client.postForObject("http://localhost:8080/api/comment", commentData, CommentDto.Get.class);
        return "redirect:/training?id=" + id;
    }

}
