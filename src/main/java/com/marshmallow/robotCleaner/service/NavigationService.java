package com.marshmallow.robotCleaner.service;

import com.marshmallow.robotCleaner.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class NavigationService {

    public ResponseModel navigate(Instructions instructions){
        // Initialise area and starting point for robot
        Coordinates coordinates = new Coordinates(instructions.getAreaSize());
        Area area = new Area(coordinates);
        Position startingPosition = new Position(instructions.getStartingPosition(), coordinates);
        List<List<Integer>> oilPatches = instructions.getOilPatches();
        IntStream.range(0, oilPatches.size())
                .forEach(index -> {
                    area.addOilPatch(oilPatches.get(index).get(0),oilPatches.get(index).get(1), index);
                });

        //clean area
        CleaningService cleaningService = new CleaningService(area, startingPosition);
        ResponseModel responseModel = cleaningService.clean(instructions.getNavigationInstructions());

        return responseModel;
    }
}
