package com.example.backend.services;

import com.example.backend.entities.Location;

import java.util.List;

public interface LocationService {

    List<Location> getLocationsByZip(String zip);

    List<Location> getLocationsByCity(String city);

}
