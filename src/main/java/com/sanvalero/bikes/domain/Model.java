package com.sanvalero.bikes.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "model")
public class Model {

    @Schema(description = "Model identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Model name", example = "stump jumper", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Model type", example = "trail")
    @Column
    private String Type;

    @Schema(description = "Model's brand identity")
    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;
}
