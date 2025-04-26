package com.example.backend.dtos;

import com.example.backend.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Name is required.")
    private String name;

    @Email(message = "Invalid email.")
    @NotBlank(message = "Email is required.")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private Integer age;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    @NotNull
    private Gender gender;

    @NotNull
    private boolean isRoommateNeeded;

    @NotNull
    private boolean isHousingNeeded;

    @NotNull
    @Valid
    private BioDTO bio;
}
