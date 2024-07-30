package de.earzt.backend.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotCreatedResponse {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private boolean isAvailable;
    private String doctorId;
    private String patientId;
}
