package de.earzt.backend.services.concretes;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.mappers.ModelMapperService;
import de.earzt.backend.models.Appointment;
import de.earzt.backend.models.Doctor;
import de.earzt.backend.models.Patient;
import de.earzt.backend.models.enums.AppointmentStatus;
import de.earzt.backend.repositories.AppointmentRepository;
import de.earzt.backend.repositories.DoctorRepository;
import de.earzt.backend.repositories.PatientRepository;
import de.earzt.backend.services.abstracts.AppointmentService;
import de.earzt.backend.services.abstracts.IdService;
import de.earzt.backend.services.dtos.requests.AppointmentRequest;
import de.earzt.backend.services.dtos.responses.AppointmentCreatedResponse;
import de.earzt.backend.services.dtos.responses.AppointmentGetAllResponse;
import de.earzt.backend.services.messages.AppointmentMessage;
import de.earzt.backend.services.messages.DoctorMessage;
import de.earzt.backend.services.messages.PatientMessage;
import de.earzt.backend.services.rules.AppointmentRule;
import de.earzt.backend.services.rules.DoctorRule;
import de.earzt.backend.services.rules.PatientRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final IdService idService;
    private final AppointmentRule appointmentRule;
    private final DoctorRule doctorRule;
    private final PatientRule patientRule;
    private final ModelMapperService modelMapperService;

    @Override
    public List<AppointmentGetAllResponse> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(appointment -> modelMapperService.forResponse().map(appointment, AppointmentGetAllResponse.class)).toList();
    }

    @Override
    public AppointmentCreatedResponse getAppointmentById(String id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(AppointmentMessage.APPOINTMENT_NOT_FOUND));
        return modelMapperService.forResponse().map(appointment, AppointmentCreatedResponse.class);
    }

    @Override
    public AppointmentCreatedResponse addAppointment(AppointmentRequest appointmentRequest) {
        doctorRule.checkIfDoctorStatusPassive(appointmentRequest.getDoctorId());
        patientRule.checkIfPatientStatusPassive(appointmentRequest.getPatientId());
        Appointment appointment = modelMapperService.forRequest().map(appointmentRequest, Appointment.class);
        Doctor selectedDoctor = doctorRepository.findById(appointmentRequest.getDoctorId()).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        Patient selectedPatient = patientRepository.findById(appointmentRequest.getPatientId()).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        appointment.setId(idService.generateAppointmentId());
        appointment.setStatus(AppointmentStatus.ACTIVE);
        appointment.setDoctor(selectedDoctor);
        appointment.setPatient(selectedPatient);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment = appointmentRepository.save(appointment);
        return modelMapperService.forResponse().map(appointment, AppointmentCreatedResponse.class);
    }

    @Override
    public AppointmentCreatedResponse updateAppointment(String id, AppointmentRequest appointmentRequest) {
        doctorRule.checkIfDoctorStatusPassive(appointmentRequest.getDoctorId());
        patientRule.checkIfPatientStatusPassive(appointmentRequest.getPatientId());
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(AppointmentMessage.APPOINTMENT_NOT_FOUND));
        Doctor selectedDoctor = doctorRepository.findById(appointmentRequest.getDoctorId()).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        Patient selectedPatient = patientRepository.findById(appointmentRequest.getPatientId()).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        appointment.setDoctor(selectedDoctor);
        appointment.setPatient(selectedPatient);
        appointment.setAppointmentTime(appointmentRequest.getAppointmentTime());
        appointment.setUpdatedAt(LocalDateTime.now());
        appointment = appointmentRepository.save(appointment);
        return modelMapperService.forResponse().map(appointment, AppointmentCreatedResponse.class);
    }

    @Override
    public AppointmentCreatedResponse updateAppointmentStatus(String id, String status) {
        appointmentRule.checkIfAppointmentStatusNameExists(status);
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(AppointmentMessage.APPOINTMENT_NOT_FOUND));
        appointment.setStatus(AppointmentStatus.valueOf(status));
        appointment = appointmentRepository.save(appointment);
        return modelMapperService.forResponse().map(appointment, AppointmentCreatedResponse.class);
    }
}
