package com.example.backend.repositories.jpa;

import com.example.backend.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    List<Location> findByCityStartingWithIgnoreCase(String city);

    List<Location> findByZipStartingWith(String city);

}
