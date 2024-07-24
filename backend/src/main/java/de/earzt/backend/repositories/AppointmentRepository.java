package de.earzt.backend.repositories;

import de.earzt.backend.models.Appointment;
import de.earzt.backend.models.enums.AppointmentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByDoctorIdAndStatus(String doctorId, AppointmentStatus appointmentStatus);

    List<Appointment> findByPatientIdAndStatus(String patientId, AppointmentStatus appointmentStatus);
}
