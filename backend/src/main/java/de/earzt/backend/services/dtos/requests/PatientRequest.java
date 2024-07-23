package de.earzt.backend.services.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NotNull
public class PatientRequest {
    @Size(min = 2, max = 50, message = "Patient name must be between 2-50 characters")
    private String name;
    @Size(min = 2, max = 50, message = "Patient contact must be between 2-50 characters")
    private String contact;
}
