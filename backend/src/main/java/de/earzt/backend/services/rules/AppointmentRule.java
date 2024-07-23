package de.earzt.backend.services.rules;

import de.earzt.backend.core.exceptions.types.RecordNotFoundException;
import de.earzt.backend.models.enums.AppointmentStatus;
import de.earzt.backend.services.messages.AppointmentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentRule {
    public void checkIfAppointmentStatusNameExists(String statusName) {
        try {
            AppointmentStatus.valueOf(statusName);
        } catch (IllegalArgumentException e) {
            throw new RecordNotFoundException(AppointmentMessage.APPOINTMENT_STATUS_NOT_FOUND);
        }
    }
}
