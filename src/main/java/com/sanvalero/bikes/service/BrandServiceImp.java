package com.sanvalero.bikes.service;

import com.sanvalero.bikes.domain.Brand;
import com.sanvalero.bikes.domain.Shop;
import com.sanvalero.bikes.domain.dto.BrandDTO;
import com.sanvalero.bikes.exception.BrandNotFoundException;
import com.sanvalero.bikes.exception.ShopNotFoundException;
import com.sanvalero.bikes.repository.BrandRepository;
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
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Set<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand addBrandToShop(long id, BrandDTO brandDTO) {
        Brand newBrand = new Brand();
        setBrand(newBrand, brandDTO);
        newBrand = brandRepository.save(newBrand);
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
        newBrand.setShop(shop);

        brandRepository.save(newBrand);

        return newBrand;
    }

    @Override
    public Brand modifyBrand(long id, BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        setBrand(brand, brandDTO);
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        brandRepository.delete(brand);
    }

    public void setBrand(Brand brand, BrandDTO brandDTO) {
        brand.setName(brandDTO.getName());
        brand.setBrandDate(brandDTO.getBrandDate());
    }
}
