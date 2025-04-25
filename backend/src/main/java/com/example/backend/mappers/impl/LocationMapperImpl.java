package com.example.backend.mappers.impl;

import com.example.backend.dtos.LocationDTO;
import com.example.backend.entities.Location;
import com.example.backend.exceptions.RecordNotFoundException;
import com.example.backend.mappers.LocationMapper;
import com.example.backend.repositories.jpa.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationMapperImpl implements LocationMapper {

    private final LocationRepository locationRepository;

    @Override
    public Location fromDto(LocationDTO locationDTO) {
        return this.locationRepository.findById(locationDTO.getId()).orElseThrow(() ->
                new RecordNotFoundException("Location with ID " + locationDTO.getId() + " not found"));
    }

    @Override
    public LocationDTO toDto(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .country(location.getCountry())
                .city(location.getCity())
                .state(location.getState())
                .zip(location.getZip())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
