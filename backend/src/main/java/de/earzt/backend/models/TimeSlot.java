package de.earzt.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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
    private boolean isAvailable;
    private Doctor doctor;
    private Patient patient;
}
