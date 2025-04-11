package com.example.backend.dtos;

import com.example.backend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;

    private String name;

    private String email;

    private LocalDate dob;

    private Integer age;

    private String phoneNumber;

    private Gender gender;

    private boolean isRoommateNeeded;

    private boolean isHousingNeeded;

    private ListingDTO listing;

    private PreferenceDTO preferences;

    private BioDTO bio;
}
