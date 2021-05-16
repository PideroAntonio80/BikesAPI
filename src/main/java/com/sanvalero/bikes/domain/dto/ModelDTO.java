package com.sanvalero.bikes.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@NoArgsConstructor
public class ModelDTO {

    @Schema(description = "Model name", example = "stump jumper", required = true)
    private String name;

    @Schema(description = "Model type", example = "trail")
    private String Type;
}
