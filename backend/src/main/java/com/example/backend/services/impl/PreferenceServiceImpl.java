package com.example.backend.services.impl;

import com.example.backend.entities.Preference;
import com.example.backend.exceptions.RecordNotFoundException;
import com.example.backend.repositories.PreferenceRepository;
import com.example.backend.services.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;

    @Override
    public Preference create(Preference preference) {
        return this.preferenceRepository.save(preference);
    }

    @Override
    public Preference update(Preference preference) {
        Preference toUpdate = this.preferenceRepository.findByIdAndDeletedAtIsNull(preference.getId())
                .orElseThrow(() -> new RecordNotFoundException(String.format("Preference with id %s not found", preference.getId())));
        BeanUtils.copyProperties(preference, toUpdate, "id");
        return this.preferenceRepository.save(toUpdate);
    }

    @Override
    public void deleteByProfileId(Long profileId) {
        Preference preference = this.preferenceRepository.findByProfileIdAndDeletedAtIsNull(profileId)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Preference with id %s not found", profileId)));
        preference.setDeletedAt(LocalDateTime.now());
        this.preferenceRepository.save(preference);
    }

    @Override
    public Preference getByProfileId(Long profileId) {
        return this.preferenceRepository.findByProfileIdAndDeletedAtIsNull(profileId)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Preference with id %s not found", profileId)));
    }
}
