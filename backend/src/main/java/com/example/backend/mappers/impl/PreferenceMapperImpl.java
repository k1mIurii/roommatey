package com.example.backend.mappers.impl;

import com.example.backend.dtos.PreferenceDTO;
import com.example.backend.entities.Preference;
import com.example.backend.entities.Profile;
import com.example.backend.mappers.LocationMapper;
import com.example.backend.mappers.PreferenceMapper;
import com.example.backend.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PreferenceMapperImpl implements PreferenceMapper {

    private final LocationMapper locationMapper;

    private final ProfileService profileService;

    @Override
    public Preference fromDto(PreferenceDTO preferenceDTO) {
        Profile profile = this.profileService.getProfileById(preferenceDTO.getProfileId());
        if (null == profile || profile.getIsRoommateNeeded() == null || profile.getIsRoommateNeeded() || !profile.getIsHousingNeeded()) {
            throw new IllegalStateException(String.format("Profile with id: %s in illegal state", preferenceDTO.getProfileId()));
        }

        Preference preference = Preference.builder()
                .minAge(preferenceDTO.getMinAge())
                .maxAge(preferenceDTO.getMaxAge())
                .budgetMin(preferenceDTO.getBudgetMin())
                .budgetMax(preferenceDTO.getBudgetMax())
                .desiredMoveInDate(preferenceDTO.getDesiredMoveInDate())
                .desiredMoveOutDate(preferenceDTO.getDesiredMoveOutDate())
                .location(
                        locationMapper.fromDto(preferenceDTO.getLocation())
                )
                .profile(profile)
                .radius(preferenceDTO.getRadius())
                .build();
        preference.setId(preferenceDTO.getId());
        return preference;
    }

    @Override
    public PreferenceDTO toDto(Preference preference) {
        return PreferenceDTO.builder()
                .id(preference.getId())
                .minAge(preference.getMinAge())
                .maxAge(preference.getMaxAge())
                .budgetMin(preference.getBudgetMin())
                .budgetMax(preference.getBudgetMax())
                .desiredMoveInDate(preference.getDesiredMoveInDate())
                .desiredMoveOutDate(preference.getDesiredMoveOutDate())
                .location(
                        locationMapper.toDto(preference.getLocation())
                )
                .profileId(preference.getProfile().getId())
                .radius(preference.getRadius())
                .build();
    }
}
