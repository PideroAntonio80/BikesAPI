package com.sanvalero.bikes.controller;

import com.sanvalero.bikes.domain.Model;
import com.sanvalero.bikes.domain.dto.ModelDTO;
import com.sanvalero.bikes.exception.ModelNotFoundException;
import com.sanvalero.bikes.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.bikes.controller.Response.NOT_FOUND;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@RestController
@Tag(name = "models", description = "Models information")
public class ModelController {

    private final Logger logger = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ModelService modelService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al models list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Models list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Model.class)))),
            @ApiResponse(responseCode = "404", description = "Models list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bikes/models", produces = "application/json")
    public ResponseEntity<Set<Model>> getModels() {

        logger.info("Init getModels");

        Set<Model> models = modelService.findAll();

        logger.info("End getModels");

        return ResponseEntity.status(HttpStatus.OK).body(models);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get model by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model exist",
                    content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Model doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bikes/models/{id}", produces = "application/json")
    public ResponseEntity<Model> getModelById(@PathVariable long id) {

        logger.info("Init getModelById");

        Model model = modelService.findById(id)
                .orElseThrow(() -> new ModelNotFoundException(id));

        logger.info("End getModelById");

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new model into a brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Model added",
                    content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Model couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/bikes/brands/{id}/model", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Model> addModel(@PathVariable long id, @RequestBody ModelDTO modelDTO) {

        logger.info("Init addModel");

        Model addedModel = modelService.addModelToBrand(id, modelDTO);

        logger.info("End addModel");

        return new ResponseEntity<>(addedModel, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model modified",
                    content = @Content(schema = @Schema(implementation = Model.class))),
            @ApiResponse(responseCode = "404", description = "Model doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/bikes/models/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Model> modifyModel(@PathVariable long id, @RequestBody ModelDTO modelDTO) {

        logger.info("Init modifyModel");

        Model model = modelService.modifyModel(id, modelDTO);

        logger.info("End modifyModel");

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Model doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/bikes/models/{id}")
    public ResponseEntity<Response> deleteModel(@PathVariable long id) {

        logger.info("Init deleteModel");

        modelService.deleteModel(id);

        logger.info("End deleteModel");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ModelNotFoundException mnfe){
        Response response = Response.errorResponse(NOT_FOUND, mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
