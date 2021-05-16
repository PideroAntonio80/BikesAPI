package com.sanvalero.bikes.service;

import com.sanvalero.bikes.domain.Model;
import com.sanvalero.bikes.domain.dto.ModelDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

public interface ModelService {

    Set<Model> findAll();
    Optional<Model> findById(long id);

    Model addModelToBrand(long id, ModelDTO modelDTO);
    Model modifyModel(long id, ModelDTO modelDTO);
    void deleteModel(long id);
}
