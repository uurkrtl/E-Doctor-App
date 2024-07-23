package de.earzt.backend.services.rules;

import de.earzt.backend.core.exceptions.types.DuplicateRecordException;
import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.models.Specialization;
import de.earzt.backend.repositories.SpecializationRepository;
import de.earzt.backend.services.messages.SpecializationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecializationRule {
    private final SpecializationRepository specializationRepository;

    public void checkIfSpecializationNameExists(String specializationName) {
        if(specializationRepository.existsByName(specializationName)) {
            throw new DuplicateRecordException(SpecializationMessage.SPECIALIZATION_NAME_EXISTS);
        }
    }

    public void checkIfSpecializationNameExists(String id, String specializationName) {
        Specialization specialization = specializationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        if(!specializationName.equals(specialization.getName()) && specializationRepository.existsByName(specializationName)) {
            throw new DuplicateRecordException(SpecializationMessage.SPECIALIZATION_NAME_EXISTS);
        }
    }
}
