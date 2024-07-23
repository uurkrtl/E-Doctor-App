package de.earzt.backend.services.dtos.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationRequest {
    @Size(min = 2, max = 50, message = "Specialization name must be between 2-50 characters")
    private String name;
}
