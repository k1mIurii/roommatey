package com.example.backend.entities;

import com.example.backend.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Transient
    private Integer age;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToOne
    @JoinColumn(name = "bio_id")
    private Bio bio;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "roommate_needed")
    private Boolean isRoommateNeeded = Boolean.FALSE;

    @Column(name = "housing_needed")
    private Boolean isHousingNeeded = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    @OneToOne
    @JoinColumn(name = "preference_id")
    private Preference preference;
}
