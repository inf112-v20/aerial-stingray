package inf112.roborally.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.cards.ProgramCard;
import inf112.roborally.events.EventUtil;
import inf112.roborally.screens.LoseScreen;
import inf112.roborally.screens.RoboRally;
import inf112.roborally.screens.ScreenManager;
import inf112.roborally.screens.WinScreen;
import inf112.roborally.ui.Board;

/**
 * Represents the "robot"/playing piece the human player is associated with.
 */
public class Player {

    /**
     * Player color
     */
    public final Color color;

    /**
     * Player ID
     */
    private final int id;
    private final boolean[] flags = {false, false, false, false};

    /**
     * Coordinates
     */
    private Vector2 pos;
    /**
     * True if this player is a bot.
     */
    private final boolean bot;
    private Vector2 nextLaserPos;

    /**
     * Life, damage & flags
     */
    private int life = 3;
    private int damage = 0;
    /**
     * Robot alive or dead
     */
    boolean dead = false;

    /**
     * Direction
     */
    private Direction dir = Direction.NORTH;
    private Vector2 backup;
    /**
     * Holds references to current program cards this robot has.
     */
    private ProgramCard[] selectedCards;

    /**
     * True if the player wants to power down.
     */
    private boolean powerDown = false;
    /**
     * Holds references to the visible cards (human only).
     */
    private ProgramCard[] visibleCards;


    public Player(Vector2 pos, Color color, int id, boolean bot) {
        this.bot = bot;
        this.pos = pos;
        this.backup = new Vector2(pos.x, pos.y);
        this.color = color;
        this.id = id;
        this.selectedCards = new ProgramCard[5];

        if (!bot)
            visibleCards = new ProgramCard[9];
        else
            visibleCards = null;
    }

    public int selectedCards() {
        int num = 0;
        for (ProgramCard card : selectedCards)
            if (card != null) num++;

        return num;
    }

    /**
     * Adds a card to the selected card pile
     *
     * @param index The index on the screen (0, 1 .. 8) of the card.
     */
    public void addCard(int index) {
        for (int i = 0; i < RoboRally.MAX_SELECTED_CARDS; i++) {
            if (selectedCards[i] == null) {
                selectedCards[i] = visibleCards[index];  // Adding card to first spot available
                return;
            }
        }
    }

    /**
     * Removes the selected cards
     */
    public void removeCards() {
        for (int i = RoboRally.MAX_SELECTED_CARDS - 1; i > -1; i--) {
            if (selectedCards[i] != null)
                selectedCards[i] = null;
        }
    }

    public boolean isBot() {
        return this.bot;
    }

    public boolean isDead() {
        return dead;
    }

    /**
     * @return player ID
     */
    public int getID() {
        return id;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public ProgramCard[] getSelectedCards() {
        return this.selectedCards;
    }

    public void setSelectedCards(ProgramCard[] selectedCards) {
        this.selectedCards = selectedCards;
    }

    /**
     * Moves the player in a certain direction with specified num. of steps.
     * Checks that the player can go on each tile for each step.
     *
     * @param board   The board to move on
     * @param dir     The direction to move 1 step towards
     * @param steps   Number of steps to take
     * @param players The other robots in the game
     */
    public void move(Board board, Direction dir, int steps, Player[] players) {
        for (int i = 0; i < steps; i++) {
            if (isDead()) {
                return;
            }
            switch (dir) {
                case NORTH:
                    if (EventUtil.canGo(board, this, Direction.NORTH, players))
                        getPos().y++;
                    break;
                case EAST:
                    if (EventUtil.canGo(board, this, Direction.EAST, players))
                        getPos().x++;
                    break;
                case SOUTH:
                    if (EventUtil.canGo(board, this, Direction.SOUTH, players))
                        getPos().y--;
                    break;

                case WEST:
                    if (EventUtil.canGo(board, this, Direction.WEST, players))
                        getPos().x--;
                    break;

                default:
                    System.err.println("Non-valid move!");
                    break;
            }
        }
    }

    public void rotate(Boolean right) {
        int currentRotation = getDirectionInt();
        if (right)
            currentRotation = Math.floorMod((currentRotation - 1), 4);
        else
            currentRotation = (currentRotation + 1) % 4;

        switch (currentRotation) {
            case 0:
                dir = Direction.SOUTH;
                break;
            case 1:
                dir = Direction.EAST;
                break;
            case 2:
                dir = Direction.NORTH;
                break;
            case 3:
                dir = Direction.WEST;
                break;
            default:
                System.err.println("Non-valid rotation!");
                break;
        }
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction direction) {
        this.dir = direction;
    }

    public Direction getOppositeDir() {
        if (dir == Direction.NORTH)
            return Direction.SOUTH;
        else if (dir == Direction.EAST)
            return Direction.WEST;
        else if (dir == Direction.SOUTH)
            return Direction.NORTH;
        else
            return Direction.EAST;
    }

    public boolean hasAllFlags() {
        return flags[0] && flags[1] && flags[2] && flags[3];
    }

    /**
     * Returns an integer representing the direction.
     * <p>
     * 0 = south
     * 1 = east
     * 2 = north
     * 3 = west
     */
    private int getDirectionInt() {
        switch (dir) {
            case SOUTH:
                return 0;

            case EAST:
                return 1;

            case NORTH:
                return 2;

            case WEST:
                return 3;

            default:
                return -1;
        }
    }

    /**
     * Changes position to backup-pos.
     * Also sets player icon to normal-mode.
     */
    public void respawn() {
        this.dir = Direction.NORTH;
        setDead(false);
        setPos(new Vector2(backup.x, backup.y));
    }


    /**
     * Rotates 2 x 90 degrees to the right= 180 degrees
     */
    public void rotate180() {
        this.rotate(true);
        this.rotate(true);
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void setBackup(Vector2 backup) {
        this.backup = backup;
    }

    /**
     * remove one damage
     */
    public void heal() {
        if (damage > 0) {
            damage--;
        }
    }

    /**
     * Removes one life.
     */
    public void subtractLife() {
        life--;
        damage = 0;
        respawn();
        if (life <= 0) {
            ScreenManager.getInstance().setScreen(new LoseScreen());
        }
    }

    public int getDamage() {
        return damage;
    }

    /**
     * add one damage
     */
    public void takeDamage() {
        damage++;
        if (damage >= 10) {
            subtractLife();
            damage = 0;
        }
    }

    public String status() {
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

    public boolean[] getFlags() {
        return flags;
    }

    /**
     * Adds a flag to the player inventory.
     *
     * @param flagNum Flag number to add
     * @throws IllegalArgumentException when flagNum is not 1-4 (inclusive)
     */
    public void addFlag(int flagNum) {
        if (flagNum <= 0 || flagNum > 4)
            throw new IllegalArgumentException("Flag number must be between 1-4 (inclusive).");

        flags[flagNum - 1] = true;
    }

    /**
     * Checks if the player has won.
     * If the player has won, set screen to the WinScreen.
     */
    public void won() {
        if (flags[3]) {
            ScreenManager.getInstance().setScreen(new WinScreen());
        }
    }

    /**
     * Executes a Program Card.
     *
     * @param board   The board the robot is on
     * @param card    The card to execute
     * @param players Other players in the game
     */
    public void executeCard(Board board, ProgramCard card, Player[] players) {
        switch (card.getType()) {
            case TURN_RIGHT:
                this.rotate(true);
                break;

            case TURN_LEFT:
                this.rotate(false);
                break;

            case TURN_U:
                this.rotate180();
                break;

            case BACKUP:
                this.move(board, this.getOppositeDir(), 1, players);
                break;

            case MOVE1:
                this.move(board, this.getDir(), 1, players);
                break;

            case MOVE2:
                this.move(board, this.getDir(), 2, players);
                break;

            case MOVE3:
                this.move(board, this.getDir(), 3, players);
                break;

            default:
                System.err.println("Unknown type of ProgramCard.");

        }
        EventUtil.hole(board, this);
    }

    public void shootLaser(Board board) {
        Vector2 laserPos = pos;
        String wallTypeThis = EventUtil.getTileType(board, "OWalls", laserPos);
        String wallTypeNext = EventUtil.getTileType(board, "OWalls", nextLaserPos);

        switch (dir) {
            case NORTH:
                nextLaserPos = new Vector2(pos.x, pos.y + 1);
                while (!EventUtil.laserOutOfBounds(this)) {
                    if (wallTypeThis.equals("Wall_North") || wallTypeNext.equals("Wall_South"))
                        break;
                    laserPos = nextLaserPos; //TODO: Check for players and deal damage to them
                }
                break;

            case SOUTH:
                nextLaserPos = new Vector2(pos.x, pos.y-1);
                while (!EventUtil.laserOutOfBounds(this)) {
                    if (wallTypeThis.equals("Wall_South") || wallTypeNext.equals("Wall_North"))
                        break;
                    laserPos = nextLaserPos; //TODO: Check for players and deal damage to them
                }
                break;

            case WEST:
                nextLaserPos = new Vector2(pos.x-1, pos.y);
                while (!EventUtil.laserOutOfBounds(this)) {
                    if (wallTypeThis.equals("Wall_West") || wallTypeNext.equals("Wall_East"))
                        break;
                    laserPos = nextLaserPos; //TODO: Check for players and deal damage to them
                }
                break;

            case EAST:
                nextLaserPos = new Vector2(pos.x + 1, pos.y);
                while (!EventUtil.laserOutOfBounds(this)) {
                    if (wallTypeThis.equals("Wall_East") || wallTypeNext.equals("Wall_West"))
                        break;
                    laserPos = nextLaserPos; //TODO: Check for players and deal damage to them
                }
                break;
        }

    }

    public void setPowerDown(boolean powerDown) {
        this.powerDown = powerDown;
    }

    /**
     * Returns the visible cards for the human.
     */
    public ProgramCard[] getVisibleCards() {
        if (bot) System.err.println("Bots can't have visible cards!");
        return visibleCards;
    }

    public Vector2 getNextLaserPos() {
        return nextLaserPos;
    }

    public void setVisibleCards(ProgramCard[] visibleCards) {
        if (bot) System.err.println("Bots can't have visible cards!");
        this.visibleCards = visibleCards;
    }

    /////////////////////////////////////////// GRAPHICS

    /**
     * @return player direction icon
     */
    private TextureRegion getNorthTextureRegion() {
        return new TextureRegion(new Texture("player-skin/" + color + "/player-north.png"));
    }

    private TextureRegion getSouthTextureRegion() {
        return new TextureRegion(new Texture("player-skin/" + color + "/player-south.png"));
    }

    private TextureRegion getWestTextureRegion() {
        return new TextureRegion(new Texture("player-skin/" + color + "/player-west.png"));
    }

    private TextureRegion getEastTextureRegion() {
        return new TextureRegion(new Texture("player-skin/" + color + "/player-east.png"));
    }

    public TiledMapTileLayer.Cell getPlayerIcon() {
        return getPlayerNormalCell();
    }

    /**
     * TiledMapTileLayer.Cell
     */
    public TiledMapTileLayer.Cell getPlayerNormalCell() {
        TextureRegion textureRegion = getNorthTextureRegion();
        switch (dir) {
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
        TiledMapTileLayer.Cell tileCell = new TiledMapTileLayer.Cell();
        tileCell.setTile(new StaticTiledMapTile(textureRegion));

        return tileCell;
    }
}
