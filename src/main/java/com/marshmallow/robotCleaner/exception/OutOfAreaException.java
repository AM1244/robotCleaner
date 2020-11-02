package com.marshmallow.robotCleaner.exception;

public class OutOfAreaException extends NavigationServiceException{
    public OutOfAreaException(int positionOilPatch) {
        super("Coordinates for oil patch are outside of the grid area", positionOilPatch);
    }
    public OutOfAreaException(String message){
        super(message);
    }
}
