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
    private Texture texture;
    private TextureRegion[][] textureRegion;

    // Player icons
    private TiledMapTileLayer.Cell player;
    private TiledMapTileLayer.Cell playerDead;
    private TiledMapTileLayer.Cell playerWon;

    // Life, damage and flags
    private int life = 3;
    private int damage = 0;
    private boolean flag1 = false;
    private boolean flag2 = false;
    private boolean flag3 = false;
    private boolean flag4 = false;


    public Player(Vector2 pos) {
        this.pos = pos;

        // Graphics
        texture = new Texture("player.png");
        textureRegion = TextureRegion.split(texture, 60, 60);

        player = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(textureRegion[0][0]));
        playerDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(textureRegion[0][1]));
        playerWon = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(textureRegion[0][2]));

        player.setTile(new StaticTiledMapTile(textureRegion[0][0]));
    }

    public TiledMapTileLayer.Cell getPlayerIcon() {
        if (flag1 && flag2 && flag3 && flag4)
            return playerWon;
        return player;
    }

    public TiledMapTileLayer.Cell getDeadPlayerIcon() { return playerDead; }

    public TiledMapTileLayer.Cell getWonPlayerIcon() {
        return playerWon;
    }

    public Vector2 getPos() { return pos; }

    public String showStatus(){
        if (life <= 0) return "You are dead";
        String str = "Life: " + life + ", Damage: " + damage;
        if (flag3)
            str += "\n You have 3 flags";
        else if (flag2)
            str += "\n You have 2 flags";
        else if (flag1)
            str += "\n You have flag 1";

        return str;
    }

    public void hasFlag1() {
        flag1 = true;
    }

    public void hasFlag2() {
        flag2 = true;
    }

    public void hasFlag3() {
        flag3 = true;
    }

    public void hasFlag4() {
        flag4 = true;
    }

}
