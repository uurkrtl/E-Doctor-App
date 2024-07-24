package de.earzt.backend.services.concretes;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.mappers.ModelMapperService;
import de.earzt.backend.models.Doctor;
import de.earzt.backend.models.Specialization;
import de.earzt.backend.repositories.DoctorRepository;
import de.earzt.backend.repositories.SpecializationRepository;
import de.earzt.backend.services.abstracts.DoctorService;
import de.earzt.backend.services.abstracts.IdService;
import de.earzt.backend.services.dtos.requests.DoctorRequest;
import de.earzt.backend.services.dtos.responses.DoctorCreatedResponse;
import de.earzt.backend.services.dtos.responses.DoctorGetAllResponse;
import de.earzt.backend.services.messages.DoctorMessage;
import de.earzt.backend.services.messages.SpecializationMessage;
import de.earzt.backend.services.rules.DoctorRule;
import de.earzt.backend.services.rules.SpecializationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorManager implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorRule doctorRule;
    private final SpecializationRule specializationRule;
    private final IdService idService;
    private final ModelMapperService modelMapperService;

    @Override
    public List<DoctorGetAllResponse> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> modelMapperService.forResponse().map(doctor, DoctorGetAllResponse.class)).toList();
    }

    @Override
    public List<DoctorGetAllResponse> getDoctorsByActive(boolean isActive) {
        List<Doctor> doctorsByActive = doctorRepository.findByIsActive(isActive);
        return doctorsByActive.stream().map(doctor -> modelMapperService.forResponse().map(doctor, DoctorGetAllResponse.class)).toList();
    }

    @Override
    public List<DoctorGetAllResponse> getActiveDoctorsBySpecializationId(String specializationId) {
        List<Doctor> activeDoctorsBySpecialization = doctorRepository.findAllBySpecializationIdAndIsActive(specializationId, true);
        return activeDoctorsBySpecialization.stream().map(doctor -> modelMapperService.forResponse().map(doctor, DoctorGetAllResponse.class)).toList();
    }

    @Override
    public DoctorCreatedResponse getDoctorById(String id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        return modelMapperService.forResponse().map(doctor, DoctorCreatedResponse.class);
    }

    @Override
    public DoctorCreatedResponse addDoctor(DoctorRequest doctorRequest) {
        specializationRule.checkIfSpecializationStatusPassive(doctorRequest.getSpecializationId());
        Doctor doctor = modelMapperService.forRequest().map(doctorRequest, Doctor.class);
        Specialization selectedSpecialization = specializationRepository.findById(doctorRequest.getSpecializationId())
                .orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        doctor.setId(idService.generateDoctorId());
        doctor.setSpecialization(selectedSpecialization);
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setActive(true);
        doctor = doctorRepository.save(doctor);
        return modelMapperService.forResponse().map(doctor, DoctorCreatedResponse.class);
    }

    @Override
    public DoctorCreatedResponse updateDoctor(String id, DoctorRequest doctorRequest) {
        specializationRule.checkIfSpecializationStatusPassive(doctorRequest.getSpecializationId());
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        Specialization selectedSpecialization = specializationRepository.findById(doctorRequest.getSpecializationId())
                .orElseThrow(() -> new RecordNotFoundException(SpecializationMessage.SPECIALIZATION_NOT_FOUND));
        doctor.setName(doctorRequest.getName());
        doctor.setSpecialization(selectedSpecialization);
        doctor.setUpdatedAt(LocalDateTime.now());
        doctor = doctorRepository.save(doctor);
        return modelMapperService.forResponse().map(doctor, DoctorCreatedResponse.class);
    }

    @Override
    public DoctorCreatedResponse changeDoctorStatus(String id) {
        doctorRule.checkHaveDoctorActiveAppointment(id);
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        doctor.setActive(!doctor.isActive());
        doctor.setUpdatedAt(LocalDateTime.now());
        doctor = doctorRepository.save(doctor);
        return modelMapperService.forResponse().map(doctor, DoctorCreatedResponse.class);
    }
}
