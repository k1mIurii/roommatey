package com.example.backend.mappers.impl;

import com.example.backend.dtos.AddressDTO;
import com.example.backend.entities.Address;
import com.example.backend.mappers.AddressMapper;
import com.example.backend.mappers.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapperImpl implements AddressMapper {

    private final LocationMapper locationMapper;

    @Override
    public Address fromDto(AddressDTO addressDTO) {
        Address address = Address.builder()
                .location(
                        locationMapper.fromDto(addressDTO.getLocation())
                )
                .street(addressDTO.getStreet())
                .apartment(addressDTO.getApartment())
                .latitude(addressDTO.getLatitude())
                .longitude(addressDTO.getLongitude())
                .build();
        address.setId(addressDTO.getId());
        return address;
    }

    @Override
    public AddressDTO toDto(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .location(
                        locationMapper.toDto(address.getLocation())
                )
                .build();
    }
}
