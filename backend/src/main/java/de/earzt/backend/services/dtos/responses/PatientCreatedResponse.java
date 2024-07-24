package de.earzt.backend.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreatedResponse {
    private String id;
    private String name;
    private String contact;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;
}