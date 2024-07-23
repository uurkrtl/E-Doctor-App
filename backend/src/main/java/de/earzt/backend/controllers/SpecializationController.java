package de.earzt.backend.controllers;

import de.earzt.backend.services.abstracts.SpecializationService;
import de.earzt.backend.services.dtos.requests.SpecializationRequest;
import de.earzt.backend.services.dtos.responses.SpecializationCreatedResponse;
import de.earzt.backend.services.dtos.responses.SpecializationGetAllResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
@RequiredArgsConstructor
public class SpecializationController {
    private final SpecializationService specializationService;

    @GetMapping
    public List<SpecializationGetAllResponse> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }

    @GetMapping("/status/{isActive}")
    public List<SpecializationGetAllResponse> getSpecializationByActive(@PathVariable boolean isActive) {
        return specializationService.getSpecializationByActive(isActive);
    }

    @GetMapping("/{id}")
    public SpecializationCreatedResponse getSpecializationById(@PathVariable String id) {
        return specializationService.getSpecializationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpecializationCreatedResponse addSpecialization(@Valid @RequestBody SpecializationRequest specializationRequest) {
        return specializationService.addSpecialization(specializationRequest);
    }

    @PutMapping("/{id}")
    public SpecializationCreatedResponse updateSpecialization(@Valid @PathVariable String id, @RequestBody SpecializationRequest specializationRequest) {
        return specializationService.updateSpecialization(id, specializationRequest);
    }

    @PutMapping("/status/{id}")
    public SpecializationCreatedResponse changeSpecializationStatus(@PathVariable String id) {
        return specializationService.changeSpecializationStatus(id);
    }
}
