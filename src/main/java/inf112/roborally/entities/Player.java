package inf112.roborally.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the "robot"/playing piece the human player is associated with.
 */
public class Player implements Placeable {

    // Coordinates
    private Vector2 pos;

    public Player(Vector2 pos) {
        this.pos = pos;
    }


    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public TileType getType() {
        return TileType.OCCUPIED_TILE;
    }

    @Override
    public int compareTo(Placeable placeable) {
        return 0;
    }
}
