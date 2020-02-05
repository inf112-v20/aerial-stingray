package inf112.roborally.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents a placeable that can be placed on a tile.
 */
public interface Placeable extends Comparable<Placeable> {

    // Coordinates
    Vector2 getPos();

    Color getColor();

    TileType getType();
}
