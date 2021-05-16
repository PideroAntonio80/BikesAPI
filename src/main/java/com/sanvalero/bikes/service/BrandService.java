package com.sanvalero.bikes.service;

import com.sanvalero.bikes.domain.Brand;
import com.sanvalero.bikes.domain.dto.BrandDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

public interface BrandService {

    Set<Brand> findAll();
    Optional<Brand> findById(long id);

    Brand addBrandToShop(long id, BrandDTO brandDTO);
    Brand modifyBrand(long id, BrandDTO brandDTO);
    void deleteBrand(long id);
}
