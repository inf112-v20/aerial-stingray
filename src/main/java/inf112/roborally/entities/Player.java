package inf112.roborally.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the "robot"/playing piece the human player is associated with.
 */
public class Player {

    // Coordinates
    private Vector2 pos;

    // Graphics
    private Texture texture;
    private TextureRegion textureRegion;

    public Player(Vector2 pos) {
        this.pos = pos;

        // Graphics
        texture = new Texture("player.png");
        textureRegion = TextureRegion.split(texture, 60, 60)[0][0];
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Vector2 getPos() {
        return pos;
    }
}
