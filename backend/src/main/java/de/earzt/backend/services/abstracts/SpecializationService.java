package de.earzt.backend.services.abstracts;

import de.earzt.backend.services.dtos.requests.SpecializationRequest;
import de.earzt.backend.services.dtos.responses.SpecializationCreatedResponse;
import de.earzt.backend.services.dtos.responses.SpecializationGetAllResponse;

import java.util.List;

public interface SpecializationService {
    List<SpecializationGetAllResponse> getAllSpecializations();
    List<SpecializationGetAllResponse> getSpecializationByActive(boolean isActive);
    SpecializationCreatedResponse getSpecializationById(String id);
    SpecializationCreatedResponse addSpecialization(SpecializationRequest specializationRequest);
    SpecializationCreatedResponse updateSpecialization(String id, SpecializationRequest specializationRequest);
    SpecializationCreatedResponse changeSpecializationStatus(String id);
}
