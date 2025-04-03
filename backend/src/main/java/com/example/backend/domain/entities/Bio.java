package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bios")
@NoArgsConstructor
@AllArgsConstructor
public class Bio extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "bio_interest",
            joinColumns = @JoinColumn(name = "bio_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<Interest> interests;

    @ManyToMany
    @JoinTable(
            name = "bio_language",
            joinColumns = @JoinColumn(name = "bio_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> spokenLanguages;
}
