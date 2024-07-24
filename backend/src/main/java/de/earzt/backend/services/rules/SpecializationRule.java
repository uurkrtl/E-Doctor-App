package de.earzt.backend.services.rules;

import de.earzt.backend.core.exceptions.types.DuplicateRecordException;
import de.earzt.backend.core.exceptions.types.HaveActiveRecordException;
import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.exceptions.types.RecordPassiveException;
import de.earzt.backend.models.Doctor;
import de.earzt.backend.models.Specialization;
import de.earzt.backend.repositories.DoctorRepository;
import de.earzt.backend.repositories.SpecializationRepository;
import de.earzt.backend.services.messages.SpecializationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationRule {
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository doctorRepository;

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

    public void checkIfSpecializationStatusPassive(String specializationId) {
        Specialization specialization = specializationRepository.findById(specializationId).orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        if (!specialization.isActive()) {
            throw new RecordPassiveException(SpecializationMessage.SPECIALIZATION_PASSIVE);
        }
    }

    public void checkHaveSpecializationActiveDoctor(String specializationId) {
        Specialization specialization = specializationRepository.findById(specializationId).orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        List<Doctor> doctors = doctorRepository.findBySpecializationIdAndIsActive(specializationId, true);
        if (!doctors.isEmpty() && specialization.isActive()) {
            throw new HaveActiveRecordException(SpecializationMessage.SPECIALIZATION_HAVE_ACTIVE_DOCTOR);
        }
    }
}
