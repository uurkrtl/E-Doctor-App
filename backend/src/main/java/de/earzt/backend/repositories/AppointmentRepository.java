package de.earzt.backend.repositories;

import de.earzt.backend.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
