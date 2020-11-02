package com.marshmallow.robotCleaner.exception;

public class NavigationServiceException extends RuntimeException{

    public NavigationServiceException(String message) {
        super(message);
    }

    public NavigationServiceException(String message, int positionOilPatch) {
        super(message + positionOilPatch);
    }
}
