package com.example.backend.services;

import com.example.backend.entities.Preference;

public interface PreferenceService {

    Preference create(Preference preference);

    Preference update(Preference preference);

    void deleteByProfileId(Long profileId);

    Preference getByProfileId(Long profileId);
}
