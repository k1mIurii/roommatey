package com.example.backend.dtos;

import com.example.backend.entities.Preference;
import com.example.backend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceDTO {

    private Long id;

    private Integer minAge;

    private Integer maxAge;

    private Set<Gender> preferredGenders;

    private Integer budgetMin;

    private Integer budgetMax;

    private LocalDate desiredMoveInDate;

    private LocalDate desiredMoveOutDate;

    private LocationDTO location;

    private Integer radius;

    public PreferenceDTO(Preference preference) {
        this.id = preference.getId();
        this.minAge = preference.getMinAge();
        this.maxAge = preference.getMaxAge();
        this.preferredGenders = preference.getPreferredGenders();
        this.budgetMin = preference.getBudgetMin();
        this.budgetMax = preference.getBudgetMax();
        this.desiredMoveInDate = preference.getDesiredMoveInDate();
        this.location = new LocationDTO(preference.getLocation());
        this.radius = preference.getRadius();
    }
}
