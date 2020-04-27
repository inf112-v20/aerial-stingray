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

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents the "robot"/playing piece the human player is associated with.
 */
public class Player {

    /**
     * Player color
     */
    public Color color;
    /**
     * Player ID
     */
    private int id;
    /**
     * Robot alive or dead
     */
    boolean robotDead = false;

    /**
     * Graphics
     */
    private Vector2 backup;
    /**
     * Coordinates
     */
    private Vector2 pos;

    /**
     * Life, damage & flags
     */
    private int life = 3;
    private int damage = 0;
    private boolean[] flags = {false, false, false, false};

    /**
     * Direction
     */
    private Direction dir = Direction.NORTH;

    /**
     * Current rotation
     * 0 = south
     * 1 = east
     * 2 = north
     * 3 = west
     */
    private int currentRotation = 2;

    /**
     * Holds references to current program cards this robot has.
     */
    private ProgramCard[] availableCards;
    private LinkedList<ProgramCard> selectedCards;

    /**
     * True if the player wants to power down.
     */
    private boolean powerDown = false;

    private boolean AI;



    public Player(Vector2 pos, Color color, int id) {
        this.AI = true;
        this.pos = pos;
        this.backup = new Vector2(pos.x, pos.y);
        this.color = color;
        this.id = id;

        // Cards
        this.availableCards = new ProgramCard[RoboRally.NUM_CARDS_SERVED];
        this.selectedCards = new LinkedList<>();
    }

    public Player(Vector2 pos, Color color, int id, boolean AI) {
        this.AI = AI;
        this.pos = pos;
        this.backup = new Vector2(pos.x, pos.y);
        this.color = color;
        this.id = id;

        // Cards
        this.availableCards = new ProgramCard[RoboRally.NUM_CARDS_SERVED];
        this.selectedCards = new LinkedList<>();
    }

    public boolean isAI() {
        return this.AI;
    }

    public int getNumCardsCerved(){
        switch (getDamage()) {
            case 5:
                return 8;
            case 6:
                return 7;
            case 7:
                return 6;
            case 8:
                return 5;
            case 9:
                return 4;
            default:
                return 9;

        }
    }

    /**
     * Moves card from available cards to selected cards.
     *
     * @param index Index of card in available cards
     */
    public void selectCard(int index) {
        selectedCards.add(availableCards[index]);
        availableCards[index] = null;


    }

    /**
     * Moves card from selected cards to available cards.
     *
     * @param index Index of cards in selected cards
     */
    public void deselectCard(int index) {
        availableCards[index] = selectedCards.get(index);
        selectedCards.remove(index);
    }

    public boolean isPowerDown() {
        return powerDown;
    }

    public void setPowerDown(boolean val) {
        powerDown = val;
    }

    public LinkedList<ProgramCard> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(LinkedList<ProgramCard> selectedCards) {
        this.selectedCards = selectedCards;
    }

    /**
     * @return All ProgramCard's this robot holds.
     */
    public ProgramCard[] getAvailableCards() {
        return availableCards;
    }

    /**
     * @return player ID
     */
    public int getID() {
        return id;
    }

    public boolean getRobotDead(){
        return robotDead;
    }

    public void setRobotDead(boolean state){
        robotDead = state;
    }
    /**
     * @return player direction icon
     */
    private TextureRegion getNorthTextureRegion() {
        return new TextureRegion(new Texture("player-skin/"+color+"/player-north.png"));
    }
    private TextureRegion getSouthTextureRegion() {
        return new TextureRegion(new Texture("player-skin/"+color+"/player-south.png"));
    }
    private TextureRegion getWestTextureRegion() {
        return new TextureRegion(new Texture("player-skin/"+color+"/player-west.png"));
    }
    private TextureRegion getEastTextureRegion() {
        return new TextureRegion(new Texture("player-skin/"+color+"/player-east.png"));
    }

    public TiledMapTileLayer.Cell getPlayerIcon() {
        return getPlayerNormalCell();
    }

    /**
     * Moves the player in a certain direction with specified num. of steps.
     * Checks that the player can go on each tile for each step.
     *  @param board The board to move on
     * @param dir   The direction to move 1 step towards
     * @param steps Number of steps to take
     * @param players The other robots in the game
     */
    public void move(Board board, Direction dir, int steps, ArrayList<Player> players) {
        for (int i = 0; i < steps; i++) {
            if (getRobotDead()){ return; }
            switch (dir) {
                case NORTH:
                    if (EventUtil.canGo(board, this, Direction.NORTH, 1, players)){
                        getPos().y++;
                        EventUtil.hole(board, this);
                        }
                    break;
                case EAST:
                    if (EventUtil.canGo(board, this, Direction.EAST, 1, players)) {
                        getPos().x++;
                        EventUtil.hole(board, this);
                    }
                    break;
                case SOUTH:
                    if (EventUtil.canGo(board, this, Direction.SOUTH, 1, players)) {
                        getPos().y--;
                        EventUtil.hole(board, this);
                    }
                    break;

                case WEST:
                    if (EventUtil.canGo(board, this, Direction.WEST, 1, players)){
                        getPos().x--;
                        EventUtil.hole(board, this);
                    }
                    break;

                default:
                    System.err.println("Non-valid move!");
                    break;
            }
        }
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction direction){
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
     * Changes position to backup-pos.
     * Also sets player icon to normal-mode.
     */
    public void respawn() {
        this.currentRotation = 2;
        this.dir = Direction.NORTH;
        setRobotDead(false);
        setPos(new Vector2(backup.x, backup.y));
        System.out.println(backup);
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
    /**
     * remove one damage
     */
    public void healDamage() {
        if (damage > 0 ){
            damage--;
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

    /**
     * Checks if the player has won.
     * If the player has won, set screen to the WinScreen.
     */
    public void checkIfWon() {
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
    public void executeCard(Board board, ProgramCard card, ArrayList<Player> players) {
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
    }
}
