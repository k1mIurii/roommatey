package com.example.backend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

    private int status;

    private String message;

    private String details;
}
