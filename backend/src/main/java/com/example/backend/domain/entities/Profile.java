package com.example.backend.domain.entities;

import com.example.backend.domain.enums.Gender;
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

    @Column(
            name = "username",
            nullable = false,
            unique = true,
            length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
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
}
