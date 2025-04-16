package com.example.backend.dtos;

import jakarta.validation.constraints.NotBlank;
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

    private Long id;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String zip;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
