package com.sanvalero.bikes.repository;

import com.sanvalero.bikes.domain.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {
    Set<Model> findAll();
}
