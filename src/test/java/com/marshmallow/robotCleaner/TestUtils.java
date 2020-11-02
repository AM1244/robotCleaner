package com.marshmallow.robotCleaner;

import com.marshmallow.robotCleaner.model.Instructions;

import java.util.Arrays;

public abstract class TestUtils {
    public static Instructions createInstructionsDto(Integer x, Integer y) {
        return Instructions.builder()
                .areaSize(Arrays.asList(x, y))
                .startingPosition(Arrays.asList(1, 2))
                .oilPatches(Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 2), Arrays.asList(2, 3)))
                .navigationInstructions("NNESEESWNWW")
                .build();
    }

    public static Instructions createWrongInstructionsDto(Integer x, Integer y) {
        return Instructions.builder()
                .areaSize(Arrays.asList(x, y))
                .startingPosition(Arrays.asList(1, 2))
                .oilPatches(Arrays.asList(Arrays.asList(5, 0), Arrays.asList(2, 2), Arrays.asList(2, 3)))
                .navigationInstructions("NNESEESWNWW")
                .build();
    }
}
