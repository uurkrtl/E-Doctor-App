package de.earzt.backend.services.abstracts;

public interface IdService {
    String generateAppointmentId();
    String generateDoctorId();
    String generatePatientId();
    String generateSpecializationId();
}
