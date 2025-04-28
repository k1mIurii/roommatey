package com.example.backend.mappers.impl;

import com.example.backend.dtos.BioDTO;
import com.example.backend.entities.BaseEntity;
import com.example.backend.entities.Bio;
import com.example.backend.mappers.BioMapper;
import com.example.backend.repositories.jpa.HobbyRepository;
import com.example.backend.repositories.jpa.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BioMapperImpl implements BioMapper {

    private final HobbyRepository hobbyRepository;
    private final LanguageRepository languageRepository;

    @Override
    public Bio fromDto(BioDTO bioDTO) {
        Bio bio = Bio.builder()
                .content(bioDTO.getContent())
                .hobbies(new HashSet<>(this.hobbyRepository.
                                            findAllById(bioDTO.getHobbies().stream()
                                                    .map(BaseEntity::getId).collect(Collectors.toSet()))))
                .languages(new HashSet<>(this.languageRepository.
                                            findAllById(bioDTO.getLanguages().stream()
                                                    .map(BaseEntity::getId).collect(Collectors.toSet()))))
                .build();
        bio.setId(bioDTO.getId());
        return bio;
    }

    @Override
    public BioDTO toDto(Bio bio) {
        return BioDTO.builder()
                .id(bio.getId())
                .content(bio.getContent())
                .hobbies(bio.getHobbies())
                .languages(bio.getLanguages())
                .build();
    }

}
