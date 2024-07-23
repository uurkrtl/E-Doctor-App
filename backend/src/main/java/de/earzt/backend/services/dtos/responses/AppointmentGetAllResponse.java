package de.earzt.backend.services.dtos.responses;

import de.earzt.backend.models.Doctor;
import de.earzt.backend.models.Patient;
import de.earzt.backend.models.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentGetAllResponse {
    private String id;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;
}
