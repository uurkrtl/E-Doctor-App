package de.earzt.backend.controllers;

import de.earzt.backend.services.abstracts.TimeSlotService;
import de.earzt.backend.services.dtos.requests.TimeSlotMultiRequest;
import de.earzt.backend.services.dtos.responses.TimeSlotCreatedResponse;
import de.earzt.backend.services.dtos.responses.TimeSlotGetAllResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/time-slots")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @GetMapping("/doctor/{doctorId}/{slotDate}")
    public List<TimeSlotGetAllResponse> getTimeSlotsByDate(@PathVariable String doctorId, @PathVariable LocalDate slotDate) {
        return timeSlotService.getTimeSlotsByDate(slotDate, doctorId);
    }

    @PostMapping("/multi")
    @ResponseStatus(HttpStatus.CREATED)
    public List<TimeSlotGetAllResponse> addMultiTimeSlot(@RequestBody TimeSlotMultiRequest timeSlotMultiRequest) {
        return timeSlotService.addMultiTimeSlot(timeSlotMultiRequest);
    }

    @PutMapping("/status/{id}")
    public TimeSlotCreatedResponse changeTimeSlotStatus(@PathVariable String id) {
        return timeSlotService.changeTimeSlotStatus(id);
    }
}
