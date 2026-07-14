package com.sudarshan.kumar.sb_poc_2.exceptions;

public class InsufficientResourceException extends RuntimeException {

    public InsufficientResourceException(String resource, String name, Long id) {
        super("Insuffient quantity for " + resource + " with id: " + id);
    }
    
}
