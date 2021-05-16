package com.sanvalero.bikes.controller;

import com.sanvalero.bikes.domain.Shop;
import com.sanvalero.bikes.domain.dto.ShopDTO;
import com.sanvalero.bikes.exception.ShopNotFoundException;
import com.sanvalero.bikes.service.ShopService;
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
@Tag(name = "shops", description = "Shops information")
public class ShopController {

    private final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    /*--------------------------------FIND_ALL----------------------------------*/
    @Operation(summary = "Get all shops list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shop list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Shop.class)))),
            @ApiResponse(responseCode = "404", description = "Shop list failed",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bikes/shops", produces = "application/json")
    public ResponseEntity<Set<Shop>> getShops() {

        logger.info("Init getShops");

        Set<Shop> shops = shopService.findAll();

        logger.info("End getShops");

        return ResponseEntity.status(HttpStatus.OK).body(shops);
    }

    /*--------------------------------FIND_BY_ID----------------------------------*/
    @Operation(summary = "Get Shop by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shop exists",
                    content = @Content(schema = @Schema(implementation = Shop.class))),
            @ApiResponse(responseCode = "404", description = "Shop doesn't exists",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/bikes/shops/{id}", produces = "application/json")
    public ResponseEntity<Shop> getShopById(@PathVariable long id) {

        logger.info("Init getShopById");

        Shop shop = shopService.findById(id)
                .orElseThrow(() -> new ShopNotFoundException(id));

        logger.info("End getShopById");

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    /*--------------------------------ADD----------------------------------*/
    @Operation(summary = "Add new shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Shop added",
                    content = @Content(schema = @Schema(implementation = Shop.class))),
            @ApiResponse(responseCode = "404", description = "Shop couldn't be added",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value= "/bikes/shops", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Shop> addShop(@RequestBody Shop shop) {

        logger.info("Init addShop");

        Shop shopAdded = shopService.addShop(shop);

        logger.info("End addShop");

        return new ResponseEntity<>(shopAdded, HttpStatus.CREATED);
    }

    /*--------------------------------MODIFY----------------------------------*/
    @Operation(summary = "Modify shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shop modified",
                    content = @Content(schema = @Schema(implementation = Shop.class))),
            @ApiResponse(responseCode = "404", description = "Shop doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/bikes/shops/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Shop> modifyShop(@PathVariable long id, @RequestBody ShopDTO shopDTO) {

        logger.info("Init modifyShop");

        Shop shop = shopService.modifyShop(id, shopDTO);

        logger.info("End modifyShop");

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    /*--------------------------------DELETE----------------------------------*/
    @Operation(summary = "Deletes shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shop deleted",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Shop doesn't exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/bikes/shops/{id}")
    public ResponseEntity<Response> deleteShop(@PathVariable long id) {

        logger.info("Init deleteShop");

        shopService.deleteShop(id);

        logger.info("End deleteShop");

        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /*--------------------------------EXCEPTION----------------------------------*/
    @ExceptionHandler(ShopNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ShopNotFoundException snfe){
        Response response = Response.errorResponse(NOT_FOUND, snfe.getMessage());
        logger.error(snfe.getMessage(), snfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
