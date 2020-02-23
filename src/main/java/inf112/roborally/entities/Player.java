package inf112.roborally.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the "robot"/playing piece the human player is associated with.
 */
public class Player {

    // Coordinates
    private Vector2 pos;

    // Graphics
    private final String PLAYER_PATH = "player.png";

    // Life, damage and flags
    private int life = 3;
    private int damage = 0;
    private boolean flag1 = false;
    private boolean flag2 = false;
    private boolean flag3 = false;
    private boolean flag4 = false;

    private TextureRegion[][] getTextureRegion() {
        return TextureRegion.split(new Texture(PLAYER_PATH), 60, 60);
    }

    public TiledMapTileLayer.Cell getPlayerNormalCell() {
        TextureRegion textureRegion = getTextureRegion()[0][0];
        return new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(textureRegion));
    }

    public TiledMapTileLayer.Cell getPlayerDeadCell() {
        TextureRegion textureRegion = getTextureRegion()[0][1];
        return new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(textureRegion));
    }

    public TiledMapTileLayer.Cell getPlayerWonCell() {
        TextureRegion textureRegion = getTextureRegion()[0][2];
        return new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(textureRegion));
    }

    public Player(Vector2 pos) {
        this.pos = pos;
    }

    public boolean hasAllFlags() {
        return flag1 && flag2 && flag3 && flag4;
    }

    public TiledMapTileLayer.Cell getPlayerIcon() {
        if (hasAllFlags())
            return getPlayerWonCell();
        return getPlayerNormalCell();
    }

    public TiledMapTileLayer.Cell getDeadPlayerIcon() {
        return getPlayerDeadCell();
    }

    public Vector2 getPos() {
        return pos;
    }

    public String showStatus() {
        if (life <= 0) return "You are dead";
        String str = "Life: " + life + ", Damage: " + damage;
        if (hasAllFlags())
            str += "\n You have all flags";
        else if (flag3)
            str += "\n You have 3 flags";
        else if (flag2)
            str += "\n You have 2 flags";
        else if (flag1)
            str += "\n You have flag 1";

        return str;
    }

    public void addFlag1() {
        flag1 = true;
    }

    public void addFlag2() {
        flag2 = true;
    }

    public void addFlag3() {
        flag3 = true;
    }

    public void addFlag4() {
        flag4 = true;
    }
}
