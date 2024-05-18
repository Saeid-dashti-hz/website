package com.example.website.controllers;

import com.example.website.models.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {

    @GetMapping("/patient")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @PostMapping("/patient")
    public String submitPatientForm(@ModelAttribute("patient") Patient patient, BindingResult result) {
        if (result.hasErrors()) {
            return "patient-form";
        }
        // Save patient to database or perform other actions
        return "redirect:/success";
    }
}

