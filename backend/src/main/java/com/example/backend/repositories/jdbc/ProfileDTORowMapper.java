package com.example.backend.repositories.jdbc;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.enums.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDTORowMapper implements RowMapper<ProfileDTO> {
    @Override
    public ProfileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProfileDTO.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .gender(Gender.valueOf(rs.getString("gender")))
                .isRoommateNeeded(rs.getBoolean("roommate_needed"))
                .isHousingNeeded(rs.getBoolean("housing_needed"))
                .build();

    }
}
