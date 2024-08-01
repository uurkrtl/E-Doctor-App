package de.earzt.backend.services.concretes;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.mappers.ModelMapperService;
import de.earzt.backend.models.Doctor;
import de.earzt.backend.models.Patient;
import de.earzt.backend.models.TimeSlot;
import de.earzt.backend.models.enums.TimeSlotStatus;
import de.earzt.backend.repositories.DoctorRepository;
import de.earzt.backend.repositories.PatientRepository;
import de.earzt.backend.repositories.TimeSlotRepository;
import de.earzt.backend.services.abstracts.IdService;
import de.earzt.backend.services.abstracts.TimeSlotService;
import de.earzt.backend.services.dtos.requests.TimeSlotMultiRequest;
import de.earzt.backend.services.dtos.responses.TimeSlotCreatedResponse;
import de.earzt.backend.services.dtos.responses.TimeSlotGetAllResponse;
import de.earzt.backend.services.messages.DoctorMessage;
import de.earzt.backend.services.messages.PatientMessage;
import de.earzt.backend.services.messages.TimeSlotMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimeSlotManager implements TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final IdService idService;
    private final ModelMapperService modelMapperService;

    @Override
    public List<TimeSlotGetAllResponse> getTimeSlotsByDate(LocalDate slotDate, String doctorId) {
        List<TimeSlot> slots = timeSlotRepository.findAllByDateAndDoctorId(slotDate, doctorId);
        return slots.stream().map(timeSlot -> modelMapperService.forResponse().map(timeSlot, TimeSlotGetAllResponse.class)).toList();
    }

    @Override
    public List<TimeSlotGetAllResponse> addMultiTimeSlot(TimeSlotMultiRequest timeSlotMultiRequest) {
        Doctor doctor = doctorRepository.findById(timeSlotMultiRequest.getDoctorId()).orElseThrow(() -> new RecordNotFoundException(DoctorMessage.DOCTOR_NOT_FOUND));
        List<TimeSlotGetAllResponse> createdSlotList = new ArrayList<>();
        for (LocalDate slotDate = timeSlotMultiRequest.getSlotStartDate(); slotDate.isBefore(timeSlotMultiRequest.getSlotEndDate()); slotDate = slotDate.plusDays(1)) {
            for (LocalTime slotTime = timeSlotMultiRequest.getSlotStartTime(); slotTime.isBefore(timeSlotMultiRequest.getSlotEndTime()); slotTime = slotTime.plusMinutes(timeSlotMultiRequest.getTimePeriod())) {
                TimeSlot timeSlot =
                        TimeSlot.builder()
                                .id(idService.generateTimeSlotId())
                                .date(slotDate)
                                .time(slotTime)
                                .status(TimeSlotStatus.AVAILABLE)
                                .doctor(doctor)
                        .build();
                timeSlot = timeSlotRepository.save(timeSlot);
                createdSlotList.add(modelMapperService.forResponse().map(timeSlot, TimeSlotGetAllResponse.class));
            }
        }
        return createdSlotList;
    }

    @Override
    public TimeSlotCreatedResponse getTimeSlotById(String id) {
        TimeSlot timeSlot = timeSlotRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(TimeSlotMessage.TIME_SLOT_NOT_FOUND));
        return modelMapperService.forResponse().map(timeSlot, TimeSlotCreatedResponse.class);
    }

    @Override
    public TimeSlotCreatedResponse changeTimeSlotStatus(String id, String status) {
        TimeSlot slot = timeSlotRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(TimeSlotMessage.TIME_SLOT_NOT_FOUND));
        slot.setStatus(TimeSlotStatus.valueOf(status));
        slot = timeSlotRepository.save(slot);
        return modelMapperService.forResponse().map(slot, TimeSlotCreatedResponse.class);
    }

    @Override
    public TimeSlotCreatedResponse addPatientId(String slotId, String patientId) {
        TimeSlot timeSlot = timeSlotRepository.findById(slotId).orElseThrow(() -> new RecordNotFoundException(TimeSlotMessage.TIME_SLOT_NOT_FOUND));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RecordNotFoundException(PatientMessage.PATIENT_NOT_FOUND));
        timeSlot.setStatus(TimeSlotStatus.ACTIVE);
        timeSlot.setPatient(patient);
        timeSlot.setAppointmentCreatedAt(LocalDateTime.now());
        timeSlot.setVerificationCode(String.valueOf(UUID.randomUUID()).substring(3,8));
        timeSlot = timeSlotRepository.save(timeSlot);
        return modelMapperService.forResponse().map(timeSlot, TimeSlotCreatedResponse.class);
    }

    @Override
    public TimeSlotCreatedResponse removePatientId(String slotId) {
        TimeSlot timeSlot = timeSlotRepository.findById(slotId).orElseThrow(() -> new RecordNotFoundException(TimeSlotMessage.TIME_SLOT_NOT_FOUND));
        timeSlot.setStatus(TimeSlotStatus.AVAILABLE);
        timeSlot.setPatient(null);
        timeSlot.setVerificationCode("");
        timeSlot = timeSlotRepository.save(timeSlot);
        return modelMapperService.forResponse().map(timeSlot, TimeSlotCreatedResponse.class);
    }

    @Override
    public TimeSlotCreatedResponse getTimeSlotByVerificationCode(String contact, String verificationCode) {
        List<TimeSlot> timeSlots = timeSlotRepository.findAllByVerificationCode(verificationCode);
        for (TimeSlot timeSlot: timeSlots) {
            if (!timeSlot.getStatus().equals(TimeSlotStatus.AVAILABLE) && timeSlot.getPatient().getContact().equals(contact)) {
                return modelMapperService.forResponse().map(timeSlot, TimeSlotCreatedResponse.class);
            }
        }
        throw new RecordNotFoundException(TimeSlotMessage.APPOINTMENT_NOT_FOUND);
    }
}
