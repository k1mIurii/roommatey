package com.example.backend.services.impl;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.entities.Interaction;
import com.example.backend.entities.Profile;
import com.example.backend.enums.Action;
import com.example.backend.exceptions.RecordNotFoundException;
import com.example.backend.mappers.ProfileMapper;
import com.example.backend.repositories.jdbc.ProfileDao;
import com.example.backend.repositories.jpa.ProfileRepository;
import com.example.backend.services.InteractionService;
import com.example.backend.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;
    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final InteractionService interactionService;

    @Override
    public Profile createProfile(Profile profile) {
        return this.profileRepository.saveAndFlush(profile);
    }

    @Override
    public Profile updateProfile(Long id, Profile profile) {
        Profile existedProfile = this.profileRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Profile with id %s not found", id)));
        BeanUtils.copyProperties(profile, existedProfile, "id");
        return this.profileRepository.save(existedProfile);
    }

    @Override
    public Profile getProfileById(Long id) {
        return this.profileRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Profile with id %s not found", id)));
    }

    @Override
    public Profile getProfileByEmail(String email) {
        return this.profileRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Profile with email %s not found", email)));
    }

    @Override
    public void deleteProfileByEither(Long id, String email) {
        if (null == email && null == id) {
            throw new RecordNotFoundException("Unable to delete profile with null id and empty email");
        }

        Profile toDelete;
        if (null != email) {
            toDelete = this.profileRepository.findByEmailAndDeletedAtIsNull(email)
                    .orElseThrow(() -> new RecordNotFoundException(String.format("Profile with email %s not found", email)));
        } else {
            toDelete = this.profileRepository.findByIdAndDeletedAtIsNull(id)
                    .orElseThrow(() -> new RecordNotFoundException(String.format("Profile with id %s not found", id)));
        }
        toDelete.setDeletedAt(LocalDateTime.now());
        this.profileRepository.saveAndFlush(toDelete);
    }

    @Override
    public Collection<ProfileDTO> getMatchingProfiles(Long id) {
        //1.Get profiles who liked my profile
        //2.Select suitable profiles from database, except those who we already had interaction with
        //3.Create new Interactions objects and save them to database, in order to prevent duplicate profiles

        Profile profile = this.getProfileById(id);
        Set<ProfileDTO> result = new HashSet<>();

        List<Long> profileIdsThatInteractedWithProfile =
                this.interactionService.findInteractionsWhereProfileWasTargetAndAction(id, Action.LIKE).stream()
                        .map(Interaction::getProfileId).toList();

        Set<ProfileDTO> interactedProfiles = this.profileRepository.findProfilesWhereIdIn(profileIdsThatInteractedWithProfile)
                .stream().map(profileMapper::toDto)
                .collect(Collectors.toSet());

        result.addAll(interactedProfiles);

        List<ProfileDTO> profiles = null;

        if (profile.getIsHousingNeeded().equals(Boolean.TRUE) && profile.getIsRoommateNeeded().equals(Boolean.FALSE)) {
            profiles = this.profileDao.findAllProfilesWhoPostedSuitableListing(id);
        }

        if (profile.getIsHousingNeeded().equals(Boolean.FALSE) && profile.getIsRoommateNeeded().equals(Boolean.TRUE)) {
            profiles = this.profileDao.findAllSuitableRoommates(id);
        }

        if (null != profiles && !profiles.isEmpty()) {
            List<Interaction> interactions = profiles.stream()
                    .map(dto ->
                            Interaction.builder()
                                    .profileId(id)
                                    .targetProfileId(dto.getId())
                                    .build())
                    .toList();
            this.interactionService.saveInteractions(interactions);
            result.addAll(profiles);
        }

        return result;
    }
}
