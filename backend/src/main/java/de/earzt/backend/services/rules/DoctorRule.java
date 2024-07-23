package de.earzt.backend.services.rules;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.exceptions.types.RecordPassiveException;
import de.earzt.backend.models.Doctor;
import de.earzt.backend.repositories.DoctorRepository;
import de.earzt.backend.services.messages.DoctorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorRule {
    private final DoctorRepository doctorRepository;

    public void checkIfDoctorStatusPassive(String doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        if (!doctor.isActive()) {
            throw new RecordPassiveException(DoctorMessage.DOCTOR_PASSIVE);
        }
    }
}
