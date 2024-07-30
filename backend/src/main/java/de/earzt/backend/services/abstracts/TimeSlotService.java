package de.earzt.backend.services.abstracts;

import de.earzt.backend.services.dtos.requests.TimeSlotMultiRequest;
import de.earzt.backend.services.dtos.responses.TimeSlotCreatedResponse;
import de.earzt.backend.services.dtos.responses.TimeSlotGetAllResponse;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotService {
    List<TimeSlotGetAllResponse> getTimeSlotsByDate(LocalDate slotDate, String doctorId);
    List<TimeSlotGetAllResponse> addMultiTimeSlot(TimeSlotMultiRequest timeSlotMultiRequest);
    TimeSlotCreatedResponse getTimeSlotById(String id);
    TimeSlotCreatedResponse changeTimeSlotStatus(String id);
    TimeSlotCreatedResponse addPatientId(String slotId, String patientId);
}
