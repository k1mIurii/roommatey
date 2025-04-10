package com.example.backend.entities;

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

    @Column(name = "content")
    private String content;

    @ManyToMany
    @JoinTable(
            name = "bio_interests",
            joinColumns = @JoinColumn(name = "bio_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<Interest> interests;

    @ManyToMany
    @JoinTable(
            name = "bio_languages",
            joinColumns = @JoinColumn(name = "bio_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> spokenLanguages;
}
