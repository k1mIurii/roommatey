package com.example.backend.mappers;

import com.example.backend.dtos.AddressDTO;
import com.example.backend.dtos.ListingDTO;
import com.example.backend.dtos.ProfileDTO;
import com.example.backend.enums.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProfileDtoRowMapper implements RowMapper<ProfileDTO> {
    @Override
    public ProfileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AddressDTO addressDTO = AddressDTO.builder()
                .street(rs.getString("street"))
                .apartment(rs.getString("apartment"))
                .latitude(rs.getDouble("latitude"))
                .longitude(rs.getDouble("longitude"))
                .build();
        ListingDTO listingDTO = ListingDTO.builder()
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .price(rs.getInt("price"))
                .availableFrom(rs.getObject("available_from", LocalDate.class))
                .availableTo(rs.getObject("available_to", LocalDate.class))
                .isPetFriendly(rs.getBoolean("pet_friendly"))
                .isSmokeFree(rs.getBoolean("smoke_free"))
                .address(addressDTO)
                .build();

        return ProfileDTO.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .email(rs.getString("email"))
                .gender(Gender.valueOf(rs.getString("gender")))
                .isRoommateNeeded(rs.getBoolean("roommate_needed"))
                .isHousingNeeded(rs.getBoolean("housing_needed"))
                .listing(listingDTO)
                .build();

    }
}
