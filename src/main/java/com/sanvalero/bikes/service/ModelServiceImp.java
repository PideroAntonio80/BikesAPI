package com.sanvalero.bikes.service;

import com.sanvalero.bikes.domain.Brand;
import com.sanvalero.bikes.domain.Model;
import com.sanvalero.bikes.domain.dto.ModelDTO;
import com.sanvalero.bikes.exception.BrandNotFoundException;
import com.sanvalero.bikes.exception.ModelNotFoundException;
import com.sanvalero.bikes.repository.BrandRepository;
import com.sanvalero.bikes.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Service
public class ModelServiceImp implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Set<Model> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public Optional<Model> findById(long id) {
        return modelRepository.findById(id);
    }

    @Override
    public Model addModelToBrand(long id, ModelDTO modelDTO) {
        Model newModel = new Model();
        setModel(newModel, modelDTO);
        newModel = modelRepository.save(newModel);
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        newModel.setBrand(brand);

        modelRepository.save(newModel);

        return newModel;
    }

    @Override
    public Model modifyModel(long id, ModelDTO modelDTO) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        setModel(model, modelDTO);
        return modelRepository.save(model);
    }

    @Override
    public void deleteModel(long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));
        modelRepository.delete(model);
    }

    public void setModel(Model model, ModelDTO modelDTO) {
        model.setName(modelDTO.getName());
        model.setType(modelDTO.getType());
    }
}
