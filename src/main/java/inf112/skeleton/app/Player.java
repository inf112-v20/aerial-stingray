package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents the "robot"/playing piece the human player is associated with.
 */
public class Player implements Placeable {

    // Coordinates
    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
