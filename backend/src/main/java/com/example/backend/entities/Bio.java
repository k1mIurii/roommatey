package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "bios")
@NoArgsConstructor
@AllArgsConstructor
public class Bio extends BaseEntity{

    @Column(name = "content")
    private String content;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bio_interests",
            joinColumns = @JoinColumn(name = "bio_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<Hobby> hobbies;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bio_languages",
            joinColumns = @JoinColumn(name = "bio_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages;
}
