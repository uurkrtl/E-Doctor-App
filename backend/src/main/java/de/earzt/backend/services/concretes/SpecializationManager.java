package de.earzt.backend.services.concretes;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.mappers.ModelMapperService;
import de.earzt.backend.models.Specialization;
import de.earzt.backend.repositories.SpecializationRepository;
import de.earzt.backend.services.abstracts.IdService;
import de.earzt.backend.services.abstracts.SpecializationService;
import de.earzt.backend.services.dtos.requests.SpecializationRequest;
import de.earzt.backend.services.dtos.responses.SpecializationCreatedResponse;
import de.earzt.backend.services.dtos.responses.SpecializationGetAllResponse;
import de.earzt.backend.services.messages.SpecializationMessage;
import de.earzt.backend.services.rules.SpecializationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationManager implements SpecializationService {
    private final SpecializationRepository specializationRepository;
    private final IdService idService;
    private final ModelMapperService modelMapperService;
    private final SpecializationRule specializationRule;

    @Override
    public List<SpecializationGetAllResponse> getAllSpecializations() {
        List<Specialization> specializations = specializationRepository.findAll();
        return specializations.stream().map(specialization -> modelMapperService.forResponse().map(specialization, SpecializationGetAllResponse.class)).toList();
    }

    @Override
    public List<SpecializationGetAllResponse> getSpecializationByActive(boolean isActive) {
        List<Specialization> specializationsByActive = specializationRepository.findByIsActive(isActive);
        return specializationsByActive.stream().map(specialization -> modelMapperService.forResponse().map(specialization, SpecializationGetAllResponse.class)).toList();
    }

    @Override
    public SpecializationCreatedResponse getSpecializationById(String id) {
        Specialization specialization = specializationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        return modelMapperService.forResponse().map(specialization, SpecializationCreatedResponse.class);
    }

    @Override
    public SpecializationCreatedResponse addSpecialization(SpecializationRequest specializationRequest) {
        specializationRule.checkIfSpecializationNameExists(specializationRequest.getName());
        Specialization specialization = modelMapperService.forRequest().map(specializationRequest, Specialization.class);
        specialization.setId(idService.generateSpecializationId());
        specialization.setCreatedAt(LocalDateTime.now());
        specialization.setActive(true);
        specialization = specializationRepository.save(specialization);
        return modelMapperService.forResponse().map(specialization, SpecializationCreatedResponse.class);
    }

    @Override
    public SpecializationCreatedResponse updateSpecialization(String id, SpecializationRequest specializationRequest) {
        specializationRule.checkIfSpecializationNameExists(id, specializationRequest.getName());
        Specialization specialization = specializationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        specialization.setName(specializationRequest.getName());
        specialization.setUpdatedAt(LocalDateTime.now());
        specialization = specializationRepository.save(specialization);
        return modelMapperService.forResponse().map(specialization, SpecializationCreatedResponse.class);
    }

    @Override
    public SpecializationCreatedResponse changeSpecializationStatus(String id) {
        specializationRule.checkHaveSpecializationActiveDoctor(id);
        Specialization specialization = specializationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        specialization.setActive(!specialization.isActive());
        specialization.setUpdatedAt(LocalDateTime.now());
        specialization = specializationRepository.save(specialization);
        return modelMapperService.forResponse().map(specialization, SpecializationCreatedResponse.class);
    }
}
