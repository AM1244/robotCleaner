package com.marshmallow.robotCleaner.model;

import lombok.Value;

import java.util.List;

@Value
public class Coordinates {
    private Integer x;

    private Integer y;

    public Coordinates(List<Integer> values) {
        x = values.get(0);
        y = values.get(1);
    }
}
