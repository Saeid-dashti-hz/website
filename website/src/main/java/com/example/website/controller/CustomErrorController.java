package com.example.website.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Custom error handling logic
        return "error"; // Return the name of your error template
    }

    // Remove the @Override annotation and the method if not needed
    public String getErrorPath() {
        return "/error";
    }
}