package com.example.backend.entities;

import com.example.backend.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "preferences")
@NoArgsConstructor
@AllArgsConstructor
public class Preference extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "max_age")
    private Integer maxAge;

    @ElementCollection(targetClass = Gender.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "gender_preferences",
            joinColumns = @JoinColumn(name = "preference_id"))
    private Set<Gender> preferredGenders;

    @Column(name = "budget_min")
    private Integer budgetMin;

    @Column(name = "budget_max")
    private Integer budgetMax;

    @Column(name = "desired_move_in_date")
    private LocalDate desiredMoveInDate;

    @Column(name = "desired_move_out_date")
    private LocalDate desiredMoveOutDate;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "radius")
    private Integer radius;
}
