package com.example.backend.repositories;

import com.example.backend.entities.Bio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BioRepository extends JpaRepository<Bio, Long> {
}
