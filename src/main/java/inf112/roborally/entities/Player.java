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
    private Vector2 backup;

    // Graphics
    private final String PLAYER_PATH = "player.png";
    private TiledMapTileLayer.Cell playerIcon;

    // Life, damage and flags
    private int life = 3;
    private int damage = 0;
    private boolean[] flags = {false, false, false, false};

    private Directions dir = Directions.NORTH;
    private int currentRotation = 2;
    /**
     * 0 = south
     * 1 = east
     * 2 = north
     * 3 = west
     */

    public Player(Vector2 pos) {
        this.pos = pos;
    }

    private TextureRegion[][] getTextureRegion() {
        return TextureRegion.split(new Texture(PLAYER_PATH), 60, 60);
    }

    public TiledMapTileLayer.Cell getPlayerIcon() {
        if (playerIcon == null)
            playerIcon = getPlayerNormalCell();

        if (hasAllFlags())
            playerIcon = getPlayerWonCell();

        return playerIcon.setRotation(currentRotation);
    }

    public void setPlayerIcon(TiledMapTileLayer.Cell playerIcon) {
        this.playerIcon = playerIcon;
    }

    public void move(int num) {
        switch (dir) {
            case NORTH:
                getPos().y += num;
                break;
            case EAST:
                getPos().x += num;
                break;
            case SOUTH:
                getPos().y -= num;
                break;
            case WEST:
                getPos().x -= num;
                break;

            default:
                System.err.println("Non-valid move!");
                break;
        }
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


    public boolean hasAllFlags() {
        return flags[0] && flags[1] && flags[2] && flags[3];
    }

    public void rotate(Boolean right) {
        if (right)
            currentRotation = (currentRotation + 1) % 4;
        else
            currentRotation = Math.floorMod((currentRotation - 1), 4);

        switch (currentRotation) {
            case 0:
                dir = Directions.SOUTH;
                break;
            case 1:
                dir = Directions.EAST;
                break;
            case 2:
                dir = Directions.NORTH;
                break;
            case 3:
                dir = Directions.WEST;
                break;
            default:
                System.err.println("Non-valid rotation!");
                break;
        }
    }


    public Vector2 getPos() {
        return pos;
    }

    public void setBackup(Vector2 backup) {
        this.backup = backup;
    }

    public void respawn(){
        pos = backup;
    }

    public boolean[] getFlags() {
        return flags;
    }

    public String showStatus() {
        if (life <= 0) return "You are dead";
        String str = "Life: " + life + ", Damage: " + damage;
        if (hasAllFlags())
            str += "\n You have all flags";
        else if (flags[2] && flags[1] && flags[0])
            str += "\n You have 3 flags";
        else if (flags[1] && flags[0])
            str += "\n You have 2 flags";
        else if (flags[0])
            str += "\n You have flag 1";

        return str;
    }

    public void addFlag1() {
        flags[0] = true;
    }

    public void addFlag2() {
        flags[1] = true;
    }

    public void addFlag3() {
        flags[2] = true;
    }

    public void addFlag4() {
        flags[3] = true;
    }
}
