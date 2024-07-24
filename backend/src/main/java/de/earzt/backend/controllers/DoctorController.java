package de.earzt.backend.controllers;

import de.earzt.backend.services.abstracts.DoctorService;
import de.earzt.backend.services.dtos.requests.DoctorRequest;
import de.earzt.backend.services.dtos.responses.DoctorCreatedResponse;
import de.earzt.backend.services.dtos.responses.DoctorGetAllResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorGetAllResponse> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/status/{isActive}")
    public List<DoctorGetAllResponse> getDoctorsByActive(@PathVariable boolean isActive) {
        return doctorService.getDoctorsByActive(isActive);
    }

    @GetMapping("/specialization/{specializationId}")
    public List<DoctorGetAllResponse> getActiveDoctorsBySpecializationId(@PathVariable String specializationId) {
        return doctorService.getActiveDoctorsBySpecializationId(specializationId);
    }

    @GetMapping("/{id}")
    public DoctorCreatedResponse getDoctorById(@PathVariable String id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorCreatedResponse addDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
        return doctorService.addDoctor(doctorRequest);
    }

    @PutMapping("/{id}")
    public DoctorCreatedResponse updateDoctor(@Valid @PathVariable String id, @RequestBody DoctorRequest doctorRequest) {
        return doctorService.updateDoctor(id, doctorRequest);
    }

    @PutMapping("/status/{id}")
    public DoctorCreatedResponse changeDoctorStatus(@PathVariable String id) {
        return doctorService.changeDoctorStatus(id);
    }
}
