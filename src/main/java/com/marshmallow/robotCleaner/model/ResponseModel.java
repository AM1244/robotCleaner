package com.marshmallow.robotCleaner.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Value
public class ResponseModel {

    private List<Integer> finalPosition;

    private Integer oilPatchesCleaned;

    public ResponseModel(Position position, int oilPatchesCleaned) {
        this.finalPosition = Arrays.asList(position.getX(), position.getY());
        this.oilPatchesCleaned = oilPatchesCleaned;
}
}
