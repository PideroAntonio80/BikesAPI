package com.sanvalero.bikes.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@NoArgsConstructor
public class BrandDTO {

    @Schema(description = "Brand name", example = "specialized", required = true)
    private String name;

    @Schema(description = "Brand origin date", example = "08/07/1978")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate brandDate;
}
