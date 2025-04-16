package com.example.backend.services;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.entities.Profile;

import java.util.Collection;

public interface ProfileService {

    Profile createProfile(Profile profile);

    Profile updateProfile(Long profileId, Profile profile);

    Profile getProfileById(Long id);

    Profile getProfileByEmail(String email);

    void deleteProfileByEither(Long id, String email);

    Collection<ProfileDTO> getMatchingProfiles(Long id);
}
