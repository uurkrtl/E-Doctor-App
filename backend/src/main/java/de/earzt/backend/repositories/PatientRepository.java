package de.earzt.backend.repositories;

import de.earzt.backend.models.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findByIsActive(boolean isActive);

    Patient findByNameAndContact(String name, String contact);
}
