package com.example.backend.services;

import com.example.backend.dtos.ProfileDTO;

import java.util.Set;

public interface ProfileService {

    ProfileDTO createProfile(ProfileDTO profileDTO);

    ProfileDTO updateProfile(Long profileId, ProfileDTO profileDTO);

    ProfileDTO getProfileById(Long profileId);

    ProfileDTO getProfileByEmail(String email);

    void deleteProfile(Long profileId);

    Set<ProfileDTO> getMatchingProfiles(ProfileDTO profileDTO);
}
