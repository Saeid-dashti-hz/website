package com.example.website.controller;

import com.example.website.model.Patient;
import com.example.website.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("patient", new Patient());
        return "registerPage";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@ModelAttribute User user) {
        // Save the user to the database or perform any other necessary actions
        return "redirect:/success";
    }
}
