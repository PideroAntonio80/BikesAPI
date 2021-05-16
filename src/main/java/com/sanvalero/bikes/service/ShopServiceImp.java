package com.sanvalero.bikes.service;

import com.sanvalero.bikes.domain.Shop;
import com.sanvalero.bikes.domain.dto.ShopDTO;
import com.sanvalero.bikes.exception.ShopNotFoundException;
import com.sanvalero.bikes.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Service
public class ShopServiceImp implements ShopService {

    @Autowired
    private ShopRepository shopRepository;


    @Override
    public Set<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<Shop> findById(long id) {
        return shopRepository.findById(id);
    }

    @Override
    public Shop addShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop modifyShop(long id, ShopDTO shopDTO) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
        setShop(shop, shopDTO);
        return shopRepository.save(shop);
    }

    @Override
    public void deleteShop(long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
        shopRepository.delete(shop);
    }

    public void setShop(Shop shop, ShopDTO shopDTO) {
        shop.setName(shopDTO.getName());
        shop.setRepair(shopDTO.isRepair());
    }
}
