package de.earzt.backend.services.dtos.responses;

import de.earzt.backend.models.enums.TimeSlotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotGetAllResponse {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private TimeSlotStatus status;
    private String doctorId;
}
