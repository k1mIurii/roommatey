package com.example.backend.services.impl;

import com.example.backend.entities.Location;
import com.example.backend.repositories.jpa.LocationRepository;
import com.example.backend.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;


    @Override
    @Cacheable("locations")
    public List<Location> getLocationsByZip(String zip) {
        return this.locationRepository.findByZipStartingWith(zip);
    }

    @Override
    @Cacheable("locations")
    public List<Location> getLocationsByCity(String city) {
        return this.locationRepository.findByCityStartingWithIgnoreCase(city);
    }
}
