package de.earzt.backend.controllers;

import de.earzt.backend.services.abstracts.AppointmentService;
import de.earzt.backend.services.dtos.requests.AppointmentRequest;
import de.earzt.backend.services.dtos.responses.AppointmentCreatedResponse;
import de.earzt.backend.services.dtos.responses.AppointmentGetAllResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentGetAllResponse> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentCreatedResponse getAppointmentById(@PathVariable String id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentCreatedResponse addAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.addAppointment(appointmentRequest);
    }

    @PutMapping("/{id}")
    public AppointmentCreatedResponse updateAppointment(@Valid @PathVariable String id, @RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.updateAppointment(id, appointmentRequest);
    }

    @PutMapping("/status/{id}")
    public AppointmentCreatedResponse updateAppointmentStatus(@PathVariable String id, @RequestParam String status) {
        return appointmentService.updateAppointmentStatus(id, status);
    }
}
