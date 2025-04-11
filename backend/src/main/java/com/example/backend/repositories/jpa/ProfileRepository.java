package com.example.backend.repositories.jpa;

import com.example.backend.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmailAndDeletedAtIsNull(String email);

    @Query("select p from Profile p where p.id in :ids")
    List<Profile> findProfilesWhereIdIn(List<Long> ids);

    Optional<Profile> findByIdAndDeletedAtIsNull(Long id);
}
