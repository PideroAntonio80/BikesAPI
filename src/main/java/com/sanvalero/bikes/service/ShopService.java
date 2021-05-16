package com.sanvalero.bikes.service;

import com.sanvalero.bikes.domain.Shop;
import com.sanvalero.bikes.domain.dto.ShopDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

public interface ShopService {

    Set<Shop> findAll();
    Optional<Shop> findById(long id);

    Shop addShop(Shop shop);
    Shop modifyShop(long id, ShopDTO shopDTO);
    void deleteShop(long id);
}
