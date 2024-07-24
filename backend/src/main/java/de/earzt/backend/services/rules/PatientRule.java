package de.earzt.backend.services.rules;

import de.earzt.backend.core.exceptions.types.HaveActiveRecordException;
import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.exceptions.types.RecordPassiveException;
import de.earzt.backend.models.Appointment;
import de.earzt.backend.models.Patient;
import de.earzt.backend.models.enums.AppointmentStatus;
import de.earzt.backend.repositories.AppointmentRepository;
import de.earzt.backend.repositories.PatientRepository;
import de.earzt.backend.services.messages.PatientMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientRule {
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public void checkIfPatientStatusPassive(String patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        if (!patient.isActive()) {
            throw new RecordPassiveException(PatientMessage.PATIENT_PASSIVE);
        }
    }

    public void checkHavePatientActiveAppointment(String patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        List<Appointment> appointments = appointmentRepository.findByPatientIdAndStatus(patientId, AppointmentStatus.ACTIVE);
        if (!appointments.isEmpty() && patient.isActive()) {
            throw new HaveActiveRecordException(PatientMessage.PATIENT_HAVE_ACTIVE_APPOINTMENT);
        }
    }
}
