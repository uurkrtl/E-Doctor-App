package de.earzt.backend.services.dtos.responses;

import de.earzt.backend.models.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCreatedResponse {
    private String id;
    private String name;
    private Specialization specialization;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;
}
