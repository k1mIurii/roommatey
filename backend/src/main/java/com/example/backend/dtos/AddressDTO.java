package com.example.backend.dtos;

import com.example.backend.entities.Address;
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

    private LocationDTO location;

    private String street;

    private String apartment;

    private Double latitude;

    private Double longitude;
}
