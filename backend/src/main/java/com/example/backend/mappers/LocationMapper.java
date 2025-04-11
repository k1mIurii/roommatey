package com.example.backend.mappers;

import com.example.backend.dtos.LocationDTO;
import com.example.backend.entities.Location;

public interface LocationMapper {
    Location fromDto(LocationDTO locationDTO);
    LocationDTO toDto(Location location);
}
