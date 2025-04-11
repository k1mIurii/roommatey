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

    private String street;

    private String apartment;

    private Double latitude;

    private Double longitude;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.apartment = address.getApartment();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }
}
