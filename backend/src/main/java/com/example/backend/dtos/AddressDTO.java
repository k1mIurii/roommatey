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
public class AddressDTO {

    private Long id;

    @NotNull
    private LocationDTO location;

    @NotBlank
    private String street;

    @NotBlank
    private String apartment;

    @NotBlank
    private Double latitude;

    @NotBlank
    private Double longitude;
}
