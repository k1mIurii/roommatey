package com.example.backend.dtos;

import com.example.backend.entities.Interest;
import com.example.backend.entities.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BioDTO {

    private Long id;

    @NotBlank
    private String content;

    @NotNull
    private Set<Interest> interests;

    @NotNull
    private Set<Language> languages;
}
