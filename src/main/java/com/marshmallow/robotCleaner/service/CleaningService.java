package com.marshmallow.robotCleaner.service;

import com.marshmallow.robotCleaner.exception.OutOfAreaException;
import com.marshmallow.robotCleaner.model.Area;
import com.marshmallow.robotCleaner.model.Position;
import com.marshmallow.robotCleaner.model.ResponseModel;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

public class CleaningService {

    private Area area;
    private Position currentPosition;
    private Position previousPosition;
    private int cleanedOilPatches;

    public CleaningService(Area area, Position startingPosition) {
        this.area = area;
        this.currentPosition = startingPosition;
        previousPosition = startingPosition;
        cleanedOilPatches = 0;
    }

    public ResponseModel clean(String navigationInstructions){
        if (currentPosition.isValid()){
            cleanPosition(currentPosition);
        }
        else {
            throw new OutOfAreaException("point out not a valid position in the grid");
        }

        for (int i = 0; i < navigationInstructions.length(); i++) {
            moveRobot(navigationInstructions.charAt(i));
        }

        return new ResponseModel(currentPosition, cleanedOilPatches);
    }

    private void moveRobot(char navInstruction){
        previousPosition.setLocation(currentPosition);
        switch (navInstruction){
            case 'N':
                currentPosition.moveNorth();
                break;
            case 'E':
                currentPosition.moveEast();
                break;
            case 'S':
                currentPosition.moveSouth();
                break;
            case 'W':
                currentPosition.moveWest();
                break;
        }
        if (currentPosition.isValid()){
            cleanPosition(currentPosition);
        }
        else {
            currentPosition.setLocation(previousPosition);
        }
    }

    private void cleanPosition(Position position) {
        if (area.hasOilPatch(position)){
            cleanedOilPatches++;
            area.removeOilPatch(position);
        }
    }

}
