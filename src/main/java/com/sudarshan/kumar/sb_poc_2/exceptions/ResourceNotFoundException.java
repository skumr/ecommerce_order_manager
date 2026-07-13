package com.sudarshan.kumar.sb_poc_2.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + "not found with id:" + id);

    }
}
