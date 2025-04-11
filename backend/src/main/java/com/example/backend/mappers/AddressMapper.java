package com.example.backend.mappers;

import com.example.backend.dtos.AddressDTO;
import com.example.backend.entities.Address;

public interface AddressMapper {
    Address fromDto(AddressDTO addressDTO);
    AddressDTO toDto(Address address);
}
