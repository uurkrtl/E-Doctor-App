package de.earzt.backend.services.abstracts;

import de.earzt.backend.services.dtos.requests.AppointmentRequest;
import de.earzt.backend.services.dtos.responses.AppointmentCreatedResponse;
import de.earzt.backend.services.dtos.responses.AppointmentGetAllResponse;

import java.util.List;

public interface AppointmentService {
    List<AppointmentGetAllResponse> getAllAppointments();
    AppointmentCreatedResponse getAppointmentById(String id);
    AppointmentCreatedResponse addAppointment(AppointmentRequest appointmentRequest);
    AppointmentCreatedResponse updateAppointment(String id, AppointmentRequest appointmentRequest);
    AppointmentCreatedResponse updateAppointmentStatus(String id, String status);
}
