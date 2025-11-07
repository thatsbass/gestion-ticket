package com.workspace.bass.exceptions;

import lombok.Getter;

import java.util.Set;

@Getter
public class ObjectValidationException extends RuntimeException {
    private final Set<String> errors;
    private final String objectName;

    public ObjectValidationException(Set<String> errors, String objectName) {
        super("Validation failed for " + objectName);
        this.errors = errors;
        this.objectName = objectName;
    }

}
