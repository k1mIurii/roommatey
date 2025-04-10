package com.example.backend.dtos;

import com.example.backend.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private Long id;

    private String country;

    private String city;

    private String state;

    private String zip;

    private Double latitude;

    private Double longitude;

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.country = location.getCountry();
        this.city = location.getCity();
        this.state = location.getState();
        this.zip = location.getZip();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
