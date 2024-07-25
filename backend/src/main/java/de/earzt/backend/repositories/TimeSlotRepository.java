package de.earzt.backend.repositories;

import de.earzt.backend.models.TimeSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepository extends MongoRepository<TimeSlot, String > {
    List<TimeSlot> findAllByDateAndDoctorId(LocalDate slotDate, String doctorId);
}
