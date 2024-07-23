package de.earzt.backend.services.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    @NotNull(message = "Doctor cannot be empty")
    private String doctorId;
    @NotNull(message = "Patient cannot be empty")
    private String patientId;
    @NotNull(message = "Appointment Time cannot be empty")
    private LocalDateTime appointmentTime;
}
