package com.example.backend.controllers;

import com.example.backend.dtos.LocationDTO;
import com.example.backend.entities.Location;
import com.example.backend.mappers.LocationMapper;
import com.example.backend.services.LocationService;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @GetMapping(path = "/search")
    public ResponseEntity<List<LocationDTO>> getLocationsByZip(@RequestParam(name = "zip", required = false) @Size(min = 3,
                                                                           message = "Must be at least 3 characters long.") String zip,
                                                               @RequestParam(name = "city", required = false) @Size(min = 3,
                                                                       message = "Must be at least 3 characters long.") String city) {
        if (null != zip && !zip.isEmpty()) {
            List<Location> locations = this.locationService.getLocationsByZip(zip);
            return new ResponseEntity<>(
                    locations.stream().map(this.locationMapper::toDto).collect(Collectors.toList()),
                    HttpStatus.OK
            );
        }

        if (null != city && !city.isEmpty()) {
            List<Location> locations = this.locationService.getLocationsByCity(city);
            return new ResponseEntity<>(
                    locations.stream().map(this.locationMapper::toDto).collect(Collectors.toList()),
                    HttpStatus.OK
            );
        }

        throw new RuntimeException("Either zip or City are required");
    }

}
