package com.sanvalero.bikes.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */
public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException() {
        super();
    }

    public BrandNotFoundException(String message){
        super(message);
    }

    public BrandNotFoundException(long id){
        super("Brand not found: " + id);
    }
}
