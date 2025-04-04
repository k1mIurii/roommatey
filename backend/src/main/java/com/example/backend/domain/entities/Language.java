package com.example.backend.domain.entities;

import com.example.backend.domain.enums.Proficiency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "languages")
@NoArgsConstructor
@AllArgsConstructor
public class Language extends BaseEntity{

    private String name;

    @Column(name = "proficiency")
    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;

}
