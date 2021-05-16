package com.sanvalero.bikes.repository;

import com.sanvalero.bikes.domain.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
    Set<Shop> findAll();
}
