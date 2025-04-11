package com.example.backend.mappers;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.entities.Profile;

public interface ProfileMapper {
    Profile fromDto(ProfileDTO profileDTO);
    ProfileDTO toDto(Profile profile);
}
