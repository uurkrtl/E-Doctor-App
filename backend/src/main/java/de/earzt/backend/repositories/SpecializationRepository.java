package de.earzt.backend.repositories;

import de.earzt.backend.models.Specialization;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpecializationRepository extends MongoRepository<Specialization, String> {
    List<Specialization> findByIsActive(boolean isActive);

    boolean existsByName(String specializationName);
}
