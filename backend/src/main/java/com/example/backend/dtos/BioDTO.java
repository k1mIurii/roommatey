package com.example.backend.dtos;

import com.example.backend.entities.Bio;
import com.example.backend.entities.Interest;
import com.example.backend.entities.Language;
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

    private String content;

    private Set<Interest> interests;

    private Set<Language> languages;

    public BioDTO(Bio bio) {
        this.id = bio.getId();
        this.content = bio.getContent();
        this.interests = bio.getInterests();
        this.languages = bio.getSpokenLanguages();
    }
}
