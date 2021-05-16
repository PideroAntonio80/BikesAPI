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
public class ShopDTO {

    @Schema(description = "Shop name", example = "Casa Pepe", required = true)
    private String name;

    @Schema(description = "Does this shop repair bikes?", example = "true")
    private boolean repair;
}
