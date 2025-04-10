package com.example.backend.dtos;

import com.example.backend.entities.Profile;
import com.example.backend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String phoneNumber;

    private Gender gender;

    private boolean isRoommateNeeded;

    private boolean isHousingNeeded;

    private ListingDTO listing;

    private PreferenceDTO preferences;

    private BioDTO bio;

    public ProfileDTO(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.age = Period.between(profile.getDob(), LocalDate.now()).getYears();
        this.phoneNumber = profile.getPhoneNumber();
        this.gender = profile.getGender();
        this.isRoommateNeeded = profile.getIsRoommateNeeded();
        this.isHousingNeeded = profile.getIsHousingNeeded();
        this.listing = new ListingDTO(profile.getListing());
        this.preferences = new PreferenceDTO(profile.getPreference());
        this.bio = new BioDTO(profile.getBio());
    }
}
