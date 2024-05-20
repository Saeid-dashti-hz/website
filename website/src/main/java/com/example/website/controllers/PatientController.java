package com.example.website.controllers;

import com.example.website.dtos.PatientDTO;
import com.example.website.models.Patient;
import com.example.website.services.PatientService;
import com.example.website.utils.ApiException;
import com.example.website.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        LogUtil.log("Get all patients");
        return patientService.getAllPatients().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/active")
    public List<PatientDTO> getActivePatients() {
        LogUtil.log("Get active patients");
        return patientService.getActivePatients().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        LogUtil.log("Get patient by ID: " + id);
        Optional<Patient> patientOpt = patientService.getPatientById(id);
        if (patientOpt.isPresent()) {
            return ResponseEntity.ok(convertToDto(patientOpt.get()));
        } else {
            throw new ApiException("Patient not found with ID: " + id);
        }
    }

    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        LogUtil.log("Create new patient");
        Patient patient = convertToEntity(patientDTO);
        return convertToDto(patientService.savePatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        LogUtil.log("Update patient with ID: " + id);
        Optional<Patient> patientOpt = patientService.getPatientById(id);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            patient.setName(patientDTO.getName());
            patient.setFamily(patientDTO.getFamily());
            patient.setBirthDate(patientDTO.getBirthDate());
            patient.setPostalCode(patientDTO.getPostalCode());
            patient.setInsuranceType(patientDTO.getInsuranceType());
            patient.setAdditionalInfo(patientDTO.getAdditionalInfo());
            patient.setProblems(patientDTO.getProblems());
            patient.setActive(patientDTO.getActive());
            final Patient updatedPatient = patientService.savePatient(patient);
            return ResponseEntity.ok(convertToDto(updatedPatient));
        } else {
            throw new ApiException("Patient not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        LogUtil.log("Delete patient with ID: " + id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    private PatientDTO convertToDto(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setFamily(patient.getFamily());
        patientDTO.setBirthDate(patient.getBirthDate());
        patientDTO.setPostalCode(patient.getPostalCode());
        patientDTO.setInsuranceType(patient.getInsuranceType());
        patientDTO.setAdditionalInfo(patient.getAdditionalInfo());
        patientDTO.setProblems(patient.getProblems());
        patientDTO.setActive(patient.getActive());
        return patientDTO;
    }

    private Patient convertToEntity(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setName(patientDTO.getName());
        patient.setFamily(patientDTO.getFamily());
        patient.setBirthDate(patientDTO.getBirthDate());
        patient.setPostalCode(patientDTO.getPostalCode());
        patient.setInsuranceType(patientDTO.getInsuranceType());
        patient.setAdditionalInfo(patientDTO.getAdditionalInfo());
        patient.setProblems(patientDTO.getProblems());
        patient.setActive(patientDTO.getActive());
        return patient;
    }
}
