package inf112.roborally.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.RoboRally;
import inf112.roborally.events.EventHandler;
import inf112.roborally.screens.LoseScreen;
import inf112.roborally.screens.WinScreen;
import inf112.roborally.ui.Board;

/**
 * Represents the "robot"/playing piece the human player is associated with.
 */
public class Player {
    private RoboRally parent;

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

    public Player(Vector2 pos, RoboRally parent) {
        this.parent = parent;

        this.pos = pos;
        this.backup = new Vector2(pos.x,pos.y);

    }

    /**
     * @return player direction icon
     */
    private TextureRegion getNorthTextureRegion() {
        return new TextureRegion(new Texture("player-skin/green/player-north.png"));
    }
    private TextureRegion getSouthTextureRegion() {
        return new TextureRegion(new Texture("player-skin/green/player-south.png"));
    }
    private TextureRegion getWestTextureRegion() {
        return new TextureRegion(new Texture("player-skin/green/player-west.png"));
    }
    private TextureRegion getEastTextureRegion() {
        return new TextureRegion(new Texture("player-skin/green/player-east.png"));
    }

    public TiledMapTileLayer.Cell getPlayerIcon() {
        return getPlayerNormalCell();
    }

    /**
     * Moves the player in a certain direction with specified num. of steps.
     * Checks that the player can go on each tile for each step.
     *
     * @param board The board to move on
     * @param dir   The direction to move 1 step towards
     * @param steps Number of steps to take
     */
    public void move(Board board, Directions dir, int steps) {
        for (int i = 0; i < steps; i++) {
            switch (dir) {
                case NORTH:
                    if (EventHandler.canGo(board, this, Directions.NORTH, 1))
                        getPos().y++;
                    break;
                case EAST:
                    if (EventHandler.canGo(board, this, Directions.EAST, 1))
                        getPos().x++;
                    break;
                case SOUTH:
                    if (EventHandler.canGo(board, this, Directions.SOUTH, 1))
                        getPos().y--;
                    break;

                case WEST:
                    if (EventHandler.canGo(board, this, Directions.WEST, 1))
                        getPos().x--;
                    break;

                default:
                    System.err.println("Non-valid move!");
                    break;
            }
        }
    }

    public Directions getDir() {
        return dir;
    }

    public Directions getOppositeDir() {
        if (dir == Directions.NORTH)
            return Directions.SOUTH;
        else if (dir == Directions.EAST)
            return Directions.WEST;
        else if (dir == Directions.SOUTH)
            return Directions.NORTH;
        else
            return Directions.EAST;
    }

    /**
     * TiledMapTileLayer.Cell
     */
    public TiledMapTileLayer.Cell getPlayerNormalCell() {
        TextureRegion textureRegion = getNorthTextureRegion();
        switch (dir){
            case NORTH:
                textureRegion = getNorthTextureRegion();
                break;
            case EAST:
                textureRegion = getEastTextureRegion();
                break;
            case SOUTH:
                textureRegion = getSouthTextureRegion();
                break;
            case WEST:
                textureRegion = getWestTextureRegion();
                break;
            default:
                System.err.println("Non-valid direction!");
                break;
        }
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
        if (life <= 0){
            parent.setScreen(new LoseScreen(parent));
        }
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

    public void winCondition() {
        if (flags[3]){
            parent.setScreen(new WinScreen(parent));
        }
    }
}
