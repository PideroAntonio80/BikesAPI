package com.sanvalero.bikes.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */
public class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException() {
        super();
    }

    public ModelNotFoundException(String message){
        super(message);
    }

    public ModelNotFoundException(long id){
        super("Model not found: " + id);
    }
}
