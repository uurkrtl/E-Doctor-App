package de.earzt.backend.models;

import de.earzt.backend.models.enums.TimeSlotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Document(collection = "time-slots")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlot {
    @Id
    private String id;
    private LocalDate date;
    private LocalTime time;
    private TimeSlotStatus status;
    private Doctor doctor;
    private Patient patient;
    private String verificationCode;
    private LocalDateTime appointmentCreatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
