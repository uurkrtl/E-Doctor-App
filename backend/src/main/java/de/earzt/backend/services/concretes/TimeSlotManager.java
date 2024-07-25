package de.earzt.backend.services.concretes;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.core.mappers.ModelMapperService;
import de.earzt.backend.models.TimeSlot;
import de.earzt.backend.repositories.TimeSlotRepository;
import de.earzt.backend.services.abstracts.IdService;
import de.earzt.backend.services.abstracts.TimeSlotService;
import de.earzt.backend.services.dtos.requests.TimeSlotMultiRequest;
import de.earzt.backend.services.dtos.responses.TimeSlotCreatedResponse;
import de.earzt.backend.services.dtos.responses.TimeSlotGetAllResponse;
import de.earzt.backend.services.messages.TimeSlotMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotManager implements TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private final IdService idService;
    private final ModelMapperService modelMapperService;

    @Override
    public List<TimeSlotGetAllResponse> getTimeSlotsByDate(LocalDate slotDate, String doctorId) {
        List<TimeSlot> slots = timeSlotRepository.findAllByDateAndDoctorId(slotDate, doctorId);
        return slots.stream().map(timeSlot -> modelMapperService.forResponse().map(timeSlot, TimeSlotGetAllResponse.class)).toList();
    }

    @Override
    public List<TimeSlotGetAllResponse> addMultiTimeSlot(TimeSlotMultiRequest timeSlotMultiRequest) {
        List<TimeSlotGetAllResponse> createdSlotList = new ArrayList<>();
        for (LocalDate slotDate = timeSlotMultiRequest.getSlotStartDate(); slotDate.isBefore(timeSlotMultiRequest.getSlotEndDate()); slotDate = slotDate.plusDays(1)) {
            for (LocalTime slotTime = timeSlotMultiRequest.getSlotStartTime(); slotTime.isBefore(timeSlotMultiRequest.getSlotEndTime()); slotTime = slotTime.plusMinutes(timeSlotMultiRequest.getTimePeriod())) {
                TimeSlot timeSlot =
                        TimeSlot.builder()
                                .id(idService.generateTimeSlotId())
                                .date(slotDate)
                                .time(slotTime)
                                .isAvailable(true)
                                .doctorId(timeSlotMultiRequest.getDoctorId())
                        .build();
                timeSlot = timeSlotRepository.save(timeSlot);
                createdSlotList.add(modelMapperService.forResponse().map(timeSlot, TimeSlotGetAllResponse.class));
            }
        }
        return createdSlotList;
    }

    @Override
    public TimeSlotCreatedResponse changeTimeSlotStatus(String id) {
        TimeSlot slot = timeSlotRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(TimeSlotMessage.TIME_SLOT_NOT_FOUND));
        slot.setAvailable(!slot.isAvailable());
        slot = timeSlotRepository.save(slot);
        return modelMapperService.forResponse().map(slot, TimeSlotCreatedResponse.class);
    }
}
