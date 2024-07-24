package de.earzt.backend.services.rules;

import de.earzt.backend.core.exceptions.types.HaveActiveRecordException;
import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.exceptions.types.RecordPassiveException;
import de.earzt.backend.models.Appointment;
import de.earzt.backend.models.Doctor;
import de.earzt.backend.models.enums.AppointmentStatus;
import de.earzt.backend.repositories.AppointmentRepository;
import de.earzt.backend.repositories.DoctorRepository;
import de.earzt.backend.services.messages.DoctorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorRule {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public void checkIfDoctorStatusPassive(String doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        if (!doctor.isActive()) {
            throw new RecordPassiveException(DoctorMessage.DOCTOR_PASSIVE);
        }
    }

    public void checkHaveDoctorActiveAppointment(String doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndStatus(doctorId, AppointmentStatus.ACTIVE);
        if (!appointments.isEmpty()) {
            throw new HaveActiveRecordException(DoctorMessage.DOCTOR_HAVE_ACTIVE_APPOINTMENT);
        }
    }
}
