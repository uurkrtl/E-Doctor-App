package de.earzt.backend.services.dtos.responses;

import de.earzt.backend.models.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorGetAllResponse {
    private String id;
    private String name;
    private Specialization specialization;
    private boolean isActive;
}
