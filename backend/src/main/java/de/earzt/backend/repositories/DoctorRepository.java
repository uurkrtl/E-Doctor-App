package de.earzt.backend.repositories;

import de.earzt.backend.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findByIsActive(boolean isActive);

    List<Doctor> findBySpecializationIdAndIsActive(String specializationId, boolean b);
}
