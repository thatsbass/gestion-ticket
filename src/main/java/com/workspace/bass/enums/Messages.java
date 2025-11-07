package com.workspace.bass.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Messages {
    USER_NOT_FOUND("The name field is not empty!"),
    INVALID_REQUEST("Invalid request"),
    VALIDATION_FAILED("Validation failed"),
    UNAUTHORIZED("Unauthorized access");

//    private final String name;
    private final String value;

}
