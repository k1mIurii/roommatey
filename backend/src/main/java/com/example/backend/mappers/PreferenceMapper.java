package com.example.backend.mappers;

import com.example.backend.dtos.PreferenceDTO;
import com.example.backend.entities.Preference;

public interface PreferenceMapper {
    Preference fromDto(PreferenceDTO preferenceDTO);
    PreferenceDTO toDto(Preference preference);
}
