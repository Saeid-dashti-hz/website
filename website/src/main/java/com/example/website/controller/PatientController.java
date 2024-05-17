package com.example.website.controller;

import com.example.website.model.Patient;
import com.example.website.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "register"; // This should match the template name
    }

    @PostMapping("/register")
    public String registerPatient(Patient patient, Model model) {
        patientRepository.save(patient);
        model.addAttribute("patient", patient);
        return "registrationSummary"; // This should match the template name
    }
}
