package com.example.backend.mappers;

import com.example.backend.dtos.BioDTO;
import com.example.backend.entities.Bio;

public interface BioMapper {
    Bio fromDto(BioDTO bioDTO);
    BioDTO toDto(Bio bio);
}
