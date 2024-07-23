package de.earzt.backend.services.rules;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.exceptions.types.RecordPassiveException;
import de.earzt.backend.models.Patient;
import de.earzt.backend.repositories.PatientRepository;
import de.earzt.backend.services.messages.PatientMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientRule {
    private final PatientRepository patientRepository;

    public void checkIfPatientStatusPassive(String patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        if (!patient.isActive()) {
            throw new RecordPassiveException(PatientMessage.PATIENT_PASSIVE);
        }
    }
}
