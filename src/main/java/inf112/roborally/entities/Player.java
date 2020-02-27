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

    /**
     * Graphics
     */
    private final String PLAYER_PATH = "player.png";
    private Vector2 backup;

    /**
     * Coordinates
     */
    private Vector2 pos;
    private TiledMapTileLayer.Cell playerIcon;

    /**
     * Life, damage & flags
     */
    private int life = 3;
    private int damage = 0;
    private boolean[] flags = {false, false, false, false};

    /**
     * Direction
     */
    private Directions dir = Directions.NORTH;

    /**
     * Current rotation
     * 0 = south
     * 1 = east
     * 2 = north
     * 3 = west
     */
    private int currentRotation = 2;


    public Player(Vector2 pos) {
        this.pos = pos;
        this.backup = new Vector2(pos.x,pos.y);
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

    /**
     * Move in the current direction.
     *
     * @param steps Steps to move
     */
    public void move(int steps) {
        switch (dir) {
            case NORTH:
                getPos().y += steps;
                break;
            case EAST:
                getPos().x += steps;
                break;
            case SOUTH:
                getPos().y -= steps;
                break;
            case WEST:
                getPos().x -= steps;
                break;

            default:
                System.err.println("Non-valid move!");
                break;
        }
    }

    public Directions getDir() {
        return dir;
    }

    /**
     * TiledMapTileLayer.Cell
     */
    public TiledMapTileLayer.Cell getPlayerNormalCell() {
        TextureRegion textureRegion = getTextureRegion()[0][0];
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
            currentRotation = Math.floorMod((currentRotation - 1), 4);
        else
            currentRotation = (currentRotation + 1) % 4;

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

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    /**
     * Changes position to backup-pos.
     * Also sets player icon to normal-mode.
     */
    public void respawn() {
        setPos(new Vector2(backup.x, backup.y));
        System.out.println(backup);
        playerIcon = getPlayerNormalCell();
    }

    /**
     * Removes one life.
     */
    public void subtractLife() {
        life--;
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

    /**
     * Adds a flag to the player inventory.
     *
     * @param flagNum Flag number to add
     * @throws IllegalArgumentException when flagNum is not 1-4 (inclusive)
     */
    public void addFlag(int flagNum) throws IllegalArgumentException {
        if (flagNum <= 0 || flagNum > 4)
            throw new IllegalArgumentException("Flag number must be between 1-4 (inclusive).");

        flags[flagNum - 1] = true;
    }
}
