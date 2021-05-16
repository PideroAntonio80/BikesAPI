package com.sanvalero.bikes.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shop")
public class Shop {

    @Schema(description = "Shop identification code", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Shop name", example = "Casa Pepe", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Does this shop repair bikes?", example = "true")
    @Column
    private boolean repair;

    @Schema(description = "Brands list in this shop")
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE)
    private List<Brand> Brands;
}
