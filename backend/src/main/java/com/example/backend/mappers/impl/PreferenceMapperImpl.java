package com.example.backend.mappers.impl;

import com.example.backend.dtos.PreferenceDTO;
import com.example.backend.entities.Preference;
import com.example.backend.mappers.LocationMapper;
import com.example.backend.mappers.PreferenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PreferenceMapperImpl implements PreferenceMapper {

    private final LocationMapper locationMapper;

    @Override
    public Preference fromDto(PreferenceDTO preferenceDTO) {
        Preference preference = Preference.builder()
                .minAge(preferenceDTO.getMinAge())
                .maxAge(preferenceDTO.getMaxAge())
                .preferredGenders(preferenceDTO.getPreferredGenders())
                .budgetMin(preferenceDTO.getBudgetMin())
                .budgetMax(preferenceDTO.getBudgetMax())
                .desiredMoveInDate(preferenceDTO.getDesiredMoveInDate())
                .desiredMoveOutDate(preferenceDTO.getDesiredMoveOutDate())
                .location(
                        locationMapper.fromDto(preferenceDTO.getLocation())
                )
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
                .preferredGenders(preference.getPreferredGenders())
                .budgetMin(preference.getBudgetMin())
                .budgetMax(preference.getBudgetMax())
                .desiredMoveInDate(preference.getDesiredMoveInDate())
                .desiredMoveOutDate(preference.getDesiredMoveOutDate())
                .location(
                        locationMapper.toDto(preference.getLocation())
                )
                .radius(preference.getRadius())
                .build();
    }
}
