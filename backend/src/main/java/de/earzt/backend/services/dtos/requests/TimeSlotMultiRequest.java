package de.earzt.backend.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotMultiRequest {
    private String doctorId;
    private LocalDate slotStartDate;
    private LocalDate slotEndDate;
    private LocalTime slotStartTime;
    private LocalTime slotEndTime;
    private short timePeriod;
}
