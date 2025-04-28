package com.example.backend.repositories.jpa;

import com.example.backend.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByProfileIdAndDeletedAtIsNull(Long profileId);

    List<Image> findAllByListingIdAndDeletedAtIsNull(Long listingId);

    Optional<Image> findByLinkAndDeletedAtIsNull(String link);
}
