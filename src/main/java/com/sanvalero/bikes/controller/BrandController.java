package com.sanvalero.bikes.controller;

import com.sanvalero.bikes.domain.Brand;
import com.sanvalero.bikes.domain.dto.BrandDTO;
import com.sanvalero.bikes.exception.BrandNotFoundException;
import com.sanvalero.bikes.service.BrandService;
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
@Tag(name = "brands", description = "Brands information")
public class BrandController {

    private final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get al brands list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brands list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Brand.class)))),
            @ApiResponse(responseCode = "404", description = "Brand list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bikes/brands", produces = "application/json")
    public ResponseEntity<Set<Brand>> getBrands() {

        logger.info("Init getBrands");

        Set<Brand> brands = brandService.findAll();

        logger.info("End getBrands");

        return ResponseEntity.status(HttpStatus.OK).body(brands);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get brand by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand exist",
                    content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Brand doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bikes/brands/{id}", produces = "application/json")
    public ResponseEntity<Brand> getBrandById(@PathVariable long id) {

        logger.info("Init getBrandById");

        Brand brand = brandService.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));

        logger.info("End getBrandById");

        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new brand into a shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand added",
                    content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Brand couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/bikes/shops/{id}/brand", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Brand> addBrand(@PathVariable long id, @RequestBody BrandDTO brandDTO) {

        logger.info("Init addBrand");

        Brand addedBrand = brandService.addBrandToShop(id, brandDTO);

        logger.info("End addBrand");

        return new ResponseEntity<>(addedBrand, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand modified",
                    content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Brand doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/bikes/brands/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Brand> modifyBrand(@PathVariable long id, @RequestBody BrandDTO brandDTO) {

        logger.info("Init modifyBrand");

        Brand brand = brandService.modifyBrand(id, brandDTO);

        logger.info("End modifyBrand");

        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Delete brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Brand doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/bikes/brands/{id}")
    public ResponseEntity<Response> deleteBrand(@PathVariable long id) {

        logger.info("Init deleteBrand");

        brandService.deleteBrand(id);

        logger.info("End deleteBrand");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(BrandNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(BrandNotFoundException bnfe){
        Response response = Response.errorResponse(NOT_FOUND, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
