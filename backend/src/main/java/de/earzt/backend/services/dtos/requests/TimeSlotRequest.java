package de.earzt.backend.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotRequest {
    private LocalDate date;
    private LocalTime time;
    private String doctorId;
}
