package com.sanvalero.bikes.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

public class ShopNotFoundException extends RuntimeException {

    public ShopNotFoundException() {
        super();
    }

    public ShopNotFoundException(String message){
        super(message);
    }

    public ShopNotFoundException(long id){
        super("Shop not found: " + id);
    }
}
