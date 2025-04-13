package com.example.backend.dtos;

import com.example.backend.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;

    @NotBlank(message = "Name is required.")
    private String name;

    @Email(message = "Invalid email.")
    @NotBlank(message = "Email is required.")
    private String email;

    private LocalDate dob;

    private Integer age;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    @NotNull
    private Gender gender;

    private boolean isRoommateNeeded;

    private boolean isHousingNeeded;

    @Valid
    private ListingDTO listing;

    @NotNull
    @Valid
    private PreferenceDTO preferences;

    @NotNull
    @Valid
    private BioDTO bio;
}
