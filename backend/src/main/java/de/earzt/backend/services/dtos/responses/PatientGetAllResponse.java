package de.earzt.backend.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientGetAllResponse {
    private String id;
    private String name;
    private String contact;
    private boolean isActive;
}
