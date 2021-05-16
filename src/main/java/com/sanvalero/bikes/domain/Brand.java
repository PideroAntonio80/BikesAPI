package com.sanvalero.bikes.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "brand")
public class Brand {

    @Schema(description = "Brand identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Brand name", example = "specialized", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Brand origin date", example = "08/07/1978")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate brandDate;

    @Schema(description = "Brand's shop identity")
    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @Schema(description = "Brand´s models list")
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
    private List<Model> models;
}
