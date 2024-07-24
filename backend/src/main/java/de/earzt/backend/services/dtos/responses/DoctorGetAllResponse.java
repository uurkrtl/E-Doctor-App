package de.earzt.backend.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorGetAllResponse {
    private String id;
    private String name;
    private String specializationName;
    private boolean isActive;
}
