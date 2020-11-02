package com.marshmallow.robotCleaner.model;

import com.marshmallow.robotCleaner.exception.OutOfAreaException;
import lombok.Value;

@Value
public class Area {
    private Coordinates coordinates;
    private Surface[][] surfaces;

    public Area(Coordinates coordinates) {
        this.coordinates = coordinates;
        surfaces = new Surface[coordinates.getX()][coordinates.getY()];

        // Initialise grid
        for (int i = 0; i < coordinates.getX(); i++) {
            for (int j = 0; j < coordinates.getY(); j++) {
                surfaces[i][j] = Surface.SEA;
            }
        }
    }
//    public void addOilPatch(List<Integer> coordinates, int positionOilPatch){
//        addOilPatch(coordinates.get(0),coordinates.get(1),positionOilPatch);
//    }

    /**
     * Adds oil to a certain position in the map area.
     *
     * @param  x  x coordinate of the oil
     * @param  y  y coordinate of the oil
     * @throws OutOfAreaException
     */
    public void addOilPatch(int x, int y, int positionOilPatch){
        if (x>= coordinates.getX() || y>=coordinates.getY()){
            throw new OutOfAreaException(positionOilPatch);
        }
        surfaces[x][y] = Surface.OIL;

    }
    /**
     * Adds oil to a certain position in the map area.
     *
     * @param  position certain position in the map to check for oil
     */
    public void removeOilPatch(Position position){
        surfaces[position.getX()][position.getY()] = Surface.SEA;

    }

    /**
     * Checks if a certain point has oil
     *
     * @param  position  position to check
     * @return boolean   true if it has oil, false if it is clean
     */
    public boolean hasOilPatch(Position position){

        return surfaces[position.getX()][position.getY()] == Surface.OIL;
    }
}
