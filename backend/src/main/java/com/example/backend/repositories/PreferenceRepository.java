package com.example.backend.repositories;

import com.example.backend.entities.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    Optional<Preference> findByProfileIdAndDeletedAtIsNull(Long profileId);

    Optional<Preference> findByIdAndDeletedAtIsNull(Long id);
}
