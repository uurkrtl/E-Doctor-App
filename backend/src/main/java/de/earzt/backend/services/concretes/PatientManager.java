package de.earzt.backend.services.concretes;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.mappers.ModelMapperService;
import de.earzt.backend.models.Patient;
import de.earzt.backend.repositories.PatientRepository;
import de.earzt.backend.services.abstracts.IdService;
import de.earzt.backend.services.abstracts.PatientService;
import de.earzt.backend.services.dtos.requests.PatientRequest;
import de.earzt.backend.services.dtos.responses.PatientCreatedResponse;
import de.earzt.backend.services.dtos.responses.PatientGetAllResponse;
import de.earzt.backend.services.messages.PatientMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientManager implements PatientService {
    private final PatientRepository patientRepository;
    private final IdService idService;
    private final ModelMapperService modelMapperService;

    @Override
    public List<PatientGetAllResponse> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> modelMapperService.forResponse().map(patient, PatientGetAllResponse.class)).toList();
    }

    @Override
    public List<PatientGetAllResponse> getPatientsByActive(boolean isActive) {
        List<Patient> patientsByActive = patientRepository.findByIsActive(isActive);
        return patientsByActive.stream().map(patient -> modelMapperService.forResponse().map(patient, PatientGetAllResponse.class)).toList();
    }

    @Override
    public PatientCreatedResponse getPatientById(String id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        return modelMapperService.forResponse().map(patient, PatientCreatedResponse.class);
    }

    @Override
    public PatientCreatedResponse addPatient(PatientRequest patientRequest) {
        Patient patient = modelMapperService.forRequest().map(patientRequest, Patient.class);
        patient.setId(idService.generatePatientId());
        patient.setActive(true);
        patient.setCreatedAt(LocalDateTime.now());
        patient = patientRepository.save(patient);
        return modelMapperService.forResponse().map(patient, PatientCreatedResponse.class);
    }

    @Override
    public PatientCreatedResponse updatePatient(String id, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        patient.setName(patientRequest.getName());
        patient.setContact(patientRequest.getContact());
        patient.setUpdatedAt(LocalDateTime.now());
        patient = patientRepository.save(patient);
        return modelMapperService.forResponse().map(patient, PatientCreatedResponse.class);
    }

    @Override
    public PatientCreatedResponse changePatientStatus(String id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        patient.setActive(!patient.isActive());
        patient.setUpdatedAt(LocalDateTime.now());
        patient = patientRepository.save(patient);
        return modelMapperService.forResponse().map(patient, PatientCreatedResponse.class);
    }
}
