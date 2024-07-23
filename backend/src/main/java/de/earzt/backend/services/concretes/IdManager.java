package de.earzt.backend.services.concretes;

import de.earzt.backend.services.abstracts.IdService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdManager implements IdService {
    @Override
    public String generateAppointmentId() {
        return "APO-" + UUID.randomUUID();
    }

    @Override
    public String generateDoctorId() {
        return "DOC-" + UUID.randomUUID();
    }

    @Override
    public String generatePatientId() {
        return "PAT-" + UUID.randomUUID();
    }

    @Override
    public String generateSpecializationId() {
        return "SPC-" + UUID.randomUUID();
    }
}
