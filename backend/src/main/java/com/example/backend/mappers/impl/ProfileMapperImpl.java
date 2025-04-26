package com.example.backend.mappers.impl;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.entities.Profile;
import com.example.backend.mappers.BioMapper;
import com.example.backend.mappers.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
public class ProfileMapperImpl implements ProfileMapper {

    private final BioMapper bioMapper;

    @Override
    public Profile fromDto(ProfileDTO profileDTO) {
        Profile profile = Profile.builder()
                .name(profileDTO.getName())
                .email(profileDTO.getEmail())
                .phoneNumber(profileDTO.getPhoneNumber())
                .dob(profileDTO.getDob())
                .gender(profileDTO.getGender())
                .isRoommateNeeded(profileDTO.isRoommateNeeded())
                .isHousingNeeded(profileDTO.isHousingNeeded())
                .bio(
                        bioMapper.fromDto(profileDTO.getBio())
                )
                .build();
        profile.setId(profileDTO.getId());
        return profile;
    }

    @Override
    public ProfileDTO toDto(Profile profile) {
        return ProfileDTO.builder()
                .id(profile.getId())
                .name(profile.getName())
                .email(profile.getEmail())
                .phoneNumber(profile.getPhoneNumber())
                .dob(profile.getDob())
                .age(Period.between(profile.getDob(), LocalDate.now()).getYears())
                .gender(profile.getGender())
                .isRoommateNeeded(profile.getIsRoommateNeeded())
                .isHousingNeeded(profile.getIsHousingNeeded())
                .bio(
                        bioMapper.toDto(profile.getBio())
                )
                .build();
    }

}
