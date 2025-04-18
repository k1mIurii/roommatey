package com.example.backend.mappers.impl;

import com.example.backend.dtos.BioDTO;
import com.example.backend.entities.Bio;
import com.example.backend.mappers.BioMapper;
import org.springframework.stereotype.Component;

@Component
public class BioMapperImpl implements BioMapper {

    @Override
    public Bio fromDto(BioDTO bioDTO) {
        Bio bio = Bio.builder()
                .content(bioDTO.getContent())
                .interests(bioDTO.getInterests())
                .languages(bioDTO.getLanguages())
                .build();
        bio.setId(bioDTO.getId());
        return bio;
    }

    @Override
    public BioDTO toDto(Bio bio) {
        return BioDTO.builder()
                .id(bio.getId())
                .content(bio.getContent())
                .interests(bio.getInterests())
                .languages(bio.getLanguages())
                .build();
    }

}
