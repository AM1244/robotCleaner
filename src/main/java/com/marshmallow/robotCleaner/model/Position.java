package com.marshmallow.robotCleaner.model;

import lombok.*;

import java.util.List;

@Data
public class Position {
    private Coordinates coordinates;
    private Integer x;
    private Integer y;

    public Position(List<Integer> position, Coordinates coordinates) {
        x = position.get(0);
        y = position.get(1);
        this.coordinates = coordinates;
    }

    public void setLocation(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public void moveEast() {
        x++;
    }

    public void moveWest() {
        x--;
    }

    public void moveNorth() {
        y++;
    }

    public void moveSouth() {
        y--;
    }

    public boolean isValid() {
        return (coordinates != null) && (x >= 0) && (x < coordinates.getX()) && (y >= 0) && (y < coordinates.getY());
    }
}