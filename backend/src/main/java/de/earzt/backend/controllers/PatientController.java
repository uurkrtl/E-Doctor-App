package de.earzt.backend.controllers;

import de.earzt.backend.services.abstracts.PatientService;
import de.earzt.backend.services.dtos.requests.PatientRequest;
import de.earzt.backend.services.dtos.responses.PatientCreatedResponse;
import de.earzt.backend.services.dtos.responses.PatientGetAllResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientGetAllResponse> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/status/{isActive}")
    public List<PatientGetAllResponse> getPatientsByActive(@PathVariable boolean isActive) {
        return patientService.getPatientsByActive(isActive);
    }

    @GetMapping("/{id}")
    public PatientCreatedResponse getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientCreatedResponse addPatient(@Valid @RequestBody PatientRequest patientRequest) {
        return patientService.addPatient(patientRequest);
    }

    @PutMapping("/{id}")
    public PatientCreatedResponse updatePatient(@Valid @PathVariable String id, @RequestBody PatientRequest patientRequest) {
        return patientService.updatePatient(id, patientRequest);
    }

    @PutMapping("/status/{id}")
    public PatientCreatedResponse changePatientStatus(@PathVariable String id) {
        return patientService.changePatientStatus(id);
    }
}
