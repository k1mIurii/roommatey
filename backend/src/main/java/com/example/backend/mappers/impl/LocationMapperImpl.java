package com.example.backend.mappers.impl;

import com.example.backend.dtos.LocationDTO;
import com.example.backend.entities.Location;
import com.example.backend.mappers.LocationMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public Location fromDto(LocationDTO locationDTO) {
        Location location = Location.builder()
                .country(locationDTO.getCountry())
                .city(locationDTO.getCity())
                .state(locationDTO.getState())
                .zip(locationDTO.getZip())
                .latitude(locationDTO.getLatitude())
                .longitude(locationDTO.getLongitude())
                .build();
        location.setId(locationDTO.getId());
        return location;
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
