package com.example.backend.entities;

import com.example.backend.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
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

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Transient
    private Integer age;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bio_id", referencedColumnName = "id")
    private Bio bio;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "roommate_needed")
    private Boolean isRoommateNeeded = Boolean.FALSE;

    @Column(name = "housing_needed")
    private Boolean isHousingNeeded = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "listing_id", referencedColumnName = "id")
    private Listing listing;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference_id", referencedColumnName = "id")
    private Preference preference;
}
