package com.example.backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    @NotNull
    private Long id;

    private String country;

    private String city;

    private String state;

    private String zip;

    private Double latitude;

    private Double longitude;
}
