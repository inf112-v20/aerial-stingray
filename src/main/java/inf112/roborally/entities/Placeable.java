package inf112.roborally.entities;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents a placeable that can be placed on a tile.
 */
public interface Placeable extends Comparable<Placeable> {

    // Coordinates
    int getX();

    int getY();

    Color getColor();

    TileType getType();
}
