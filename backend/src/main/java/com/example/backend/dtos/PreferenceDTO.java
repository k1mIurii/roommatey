package com.example.backend.dtos;

import jakarta.validation.Valid;
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
public class PreferenceDTO {

    private Long id;

    @NotNull
    private Long profileId;

    @NotNull
    private Integer minAge;

    @NotNull
    private Integer maxAge;

    @NotNull
    private Integer budgetMin;

    @NotNull
    private Integer budgetMax;

    @NotNull
    private LocalDate desiredMoveInDate;

    @NotNull
    private LocalDate desiredMoveOutDate;

    @NotNull
    @Valid
    private LocationDTO location;

    @NotNull
    private Integer radius;
}
