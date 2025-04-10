package com.example.backend.services.impl;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.entities.Interaction;
import com.example.backend.entities.Profile;
import com.example.backend.enums.Action;
import com.example.backend.repositories.jdbc.ProfileDao;
import com.example.backend.repositories.jpa.ProfileRepository;
import com.example.backend.services.InteractionService;
import com.example.backend.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;
    private final ProfileRepository profileRepository;
    private final InteractionService interactionService;

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        return null;
    }

    @Override
    public ProfileDTO updateProfile(Long profileId, ProfileDTO profileDTO) {
        return null;
    }

    @Override
    public ProfileDTO getProfileById(Long profileId) {
        Profile profile = this.profileRepository.findByIdAndDeletedAtIsNull(profileId).orElseThrow(() -> new RuntimeException("Record Not Found"));
        return new ProfileDTO(profile);
    }

    @Override
    public ProfileDTO getProfileByEmail(String email) {
        Profile profile = this.profileRepository.findByEmailAndDeletedAtIsNull(email).orElseThrow(() -> new RuntimeException("Record Not Found"));
        return new ProfileDTO(profile);
    }

    @Override
    public void deleteProfile(Long profileId) {
        Profile profile = this.profileRepository.findByIdAndDeletedAtIsNull(profileId).orElseThrow(() -> new RuntimeException("Record not found"));
        profile.setDeletedAt(LocalDateTime.now());
        this.profileRepository.saveAndFlush(profile);
    }

    @Override
    public Set<ProfileDTO> getMatchingProfiles(ProfileDTO profileDTO) {
        //1.Get profiles who liked my profile
        //2.Select suitable profiles from database, except those who we already had interaction with
        //3.Create new Interactions objects and save them to database, in order to prevent duplicate profiles
        Set<ProfileDTO> result = new HashSet<>();
        List<Long> profileIdsThatInteractedWithProfile = this.interactionService.findInteractionsWhereProfileWasTargetAndAction(profileDTO.getId(), Action.LIKE).stream().map(Interaction::getProfileId).toList();

        Set<ProfileDTO> interactedProfiles = this.profileRepository.findProfilesWhereIdIn(profileIdsThatInteractedWithProfile).stream().map(ProfileDTO::new).collect(Collectors.toSet());
        result.addAll(interactedProfiles);

        List<ProfileDTO> profiles = null;

        if (profileDTO.isHousingNeeded() && !profileDTO.isRoommateNeeded()) {
            profiles = this.profileDao.findAllProfilesWhoPostedSuitableListing(profileDTO.getId());
        }

        if (!profileDTO.isHousingNeeded() && profileDTO.isRoommateNeeded()) {
            profiles = this.profileDao.findAllSuitableRoommates(profileDTO.getId());
        }

        if (null != profiles && !profiles.isEmpty()) {
            List<Interaction> interactions = profiles.stream()
                    .map(dto ->
                            Interaction.builder()
                                    .profileId(profileDTO.getId())
                                    .targetProfileId(dto.getId())
                                    .build())
                    .toList();
            this.interactionService.saveInteractions(interactions);
            result.addAll(profiles);
        }

        return result;
    }
}
