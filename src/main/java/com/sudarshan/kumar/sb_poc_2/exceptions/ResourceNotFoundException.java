package com.sudarshan.kumar.sb_poc_2.exceptions;

import com.sudarshan.kumar.sb_poc_2.models.Supplier;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " not found with id: " + id);
    }

    public ResourceNotFoundException(String resource, String name) {
        super(resource + " not found with name: " + name);
    }

    public ResourceNotFoundException(String resource, Supplier supplier) {
        super(resource + " not found with supplier: " + supplier.getName());
    }
}
