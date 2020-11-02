package com.marshmallow.robotCleaner.service;

import com.marshmallow.robotCleaner.model.Area;
import com.marshmallow.robotCleaner.model.Coordinates;
import com.marshmallow.robotCleaner.model.Position;
import com.marshmallow.robotCleaner.model.ResponseModel;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleaningServiceTest {

    @Test
    public void when_getting_correct_coordinates_expect_correct_final_position() {
        Coordinates coordinates = new Coordinates(Arrays.asList(5, 5));
        Area area = new Area(coordinates);

        Position position = new Position(Arrays.asList(1, 2), coordinates);
        area.addOilPatch(1, 0, 0);
        area.addOilPatch(2, 2, 1);
        area.addOilPatch(2, 3, 2);
        String navigationInstructions = "NNESEESWNWW";
        CleaningService cleaningService = new CleaningService(area, position);

        ResponseModel responseModel = cleaningService.clean(navigationInstructions);

        assertNotNull(responseModel);
        assertEquals(1, responseModel.getOilPatchesCleaned());
        assertEquals(Arrays.asList(1, 3), responseModel.getFinalPosition());
    }
}
