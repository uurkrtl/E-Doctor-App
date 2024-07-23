package de.earzt.backend.services.abstracts;

import de.earzt.backend.services.dtos.requests.PatientRequest;
import de.earzt.backend.services.dtos.responses.PatientCreatedResponse;
import de.earzt.backend.services.dtos.responses.PatientGetAllResponse;

import java.util.List;

public interface PatientService {
    List<PatientGetAllResponse> getAllPatients();
    List<PatientGetAllResponse> getPatientsByActive(boolean isActive);
    PatientCreatedResponse getPatientById(String id);
    PatientCreatedResponse addPatient(PatientRequest patientRequest);
    PatientCreatedResponse updatePatient(String id, PatientRequest patientRequest);
    PatientCreatedResponse changePatientStatus(String id);
}
