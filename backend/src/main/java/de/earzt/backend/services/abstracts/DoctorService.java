package de.earzt.backend.services.abstracts;

import de.earzt.backend.services.dtos.requests.DoctorRequest;
import de.earzt.backend.services.dtos.responses.DoctorCreatedResponse;
import de.earzt.backend.services.dtos.responses.DoctorGetAllResponse;

import java.util.List;

public interface DoctorService {
    List<DoctorGetAllResponse> getAllDoctors();
    List<DoctorGetAllResponse> getDoctorsByActive(boolean isActive);
    DoctorCreatedResponse getDoctorById(String id);
    DoctorCreatedResponse addDoctor(DoctorRequest doctorRequest);
    DoctorCreatedResponse updateDoctor(String id, DoctorRequest doctorRequest);
    DoctorCreatedResponse changeDoctorStatus(String id);
}
