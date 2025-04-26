package com.example.backend.repositories;

import com.example.backend.entities.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    Optional<Listing> findByIdAndDeletedAtIsNull(Long id);

    Optional<Listing> findByProfileIdAndDeletedAtIsNull(Long profileId);
}
