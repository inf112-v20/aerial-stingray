package inf112.roborally.events;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.Main;
import inf112.roborally.entities.Direction;
import inf112.roborally.entities.Player;
import inf112.roborally.ui.Board;

import java.util.ArrayList;

import static inf112.roborally.ui.Board.TILE_SIZE;

/**
 * Provides static methods for dealing with tile-events.
 */
public class EventUtil {

    private static boolean fromConveyor;


    /**
     * Handles an event on a current tile with a given map & player.
     * @param board  The current Board which holds all tiles
     * @param players The other robots in the game
     */
    public static void handleEvent(Board board, ArrayList<Player> players) {
        for (Player player : players) {
            expressConveyor(board, player, players);
            normalConveyor(board, player, players);
            rotators(board, player);

            lasers(board, player);
            flags(board, player);
            repairs(board, player);
        }
    }


    /**
     * If player is on a hole or outside the boar, subtract one life and set player.robotAlive to false
     *  @param board  The current Board which holds all tiles
     * @param player The player who stands on the tile
     */
    public static void hole(Board board, Player player){
        if (getTileType(board, "OEvents", player.getPos()).equals("Hole")){
            player.subtractLife();
            player.setRobotDead(true);
        } else if (EventUtil.outOfBounds(player)) {
            player.subtractLife();
            player.setRobotDead(true);
        }
    }

    /**
     * If player is on a express conveyor it moves player one step in the direction of the conveyor
     *  @param board  The current Board which holds all tiles
     * @param player The player who stands on the tile
     * @param players The other robots in the game
     */
    private static void expressConveyor(Board board, Player player, ArrayList<Player> players){
        String movers = getTileType(board, "OMovers", player.getPos());

        switch (movers) {
            case "Express_Conveyor_North":
                player.move(board, Direction.NORTH, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_West":
                player.move(board, Direction.WEST, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_South":
                player.move(board, Direction.SOUTH, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_East":
                player.move(board, Direction.EAST, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_EastNorth":
                if (fromConveyor)
                    player.rotate(false);
                player.move(board, Direction.NORTH, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_NorthEast":
                if (fromConveyor)
                    player.rotate(true);
                player.move(board, Direction.EAST, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_EastSouth":
                if (fromConveyor)
                    player.rotate(true);
                player.move(board, Direction.SOUTH, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_SouthWest":
                if (fromConveyor)
                    player.rotate(true);
                player.move(board, Direction.WEST, 2, players);
                fromConveyor = true;
                break;

            case "Express_Conveyor_WestNorth":
                if (fromConveyor)
                    player.rotate(true);
                player.move(board, Direction.NORTH, 2, players);
                fromConveyor = true;
                break;
        }
    }

    /**
     * If player is on a normal conveyor it moves player one step in the direction of the conveyor
     *  @param board  The current Board which holds all tiles
     * @param player The player who stands on the tile
     * @param players The other robots in the game
     */
    private static void normalConveyor(Board board, Player player, ArrayList<Player> players) {
        String movers = getTileType(board, "OMovers", player.getPos());

        switch (movers) {
            case "Normal_Conveyor_North":
            //case "Express_Conveyor_North":
                player.move(board, Direction.NORTH, 1, players);
                fromConveyor = true;
                break;

            case "Normal_Conveyor_East":
                player.move(board, Direction.EAST, 1, players);
                fromConveyor = true;
                break;

            case "Normal_Conveyor_South":
                player.move(board, Direction.SOUTH, 1, players);
                fromConveyor = true;
                break;

            case "Normal_Conveyor_West":
                player.move(board, Direction.WEST, 1, players);
                fromConveyor = true;
                break;

            case "Normal_Conveyor_EastNorth":
                if (fromConveyor)
                    player.rotate(false);
                player.move(board, Direction.NORTH, 1, players);
                fromConveyor = true;
                break;

            case "Normal_Conveyor_NorthEast":
                if (fromConveyor)
                    player.rotate(true);
                player.move(board, Direction.EAST, 1, players);
                fromConveyor = true;
                break;

            case "Normal_Conveyor_EastSouth":
                if (fromConveyor)
                    player.rotate(true);
                player.move(board, Direction.SOUTH, 1, players);
                fromConveyor = true;
                break;

            case "Normal_Conveyor_SouthEast":
                if (fromConveyor)
                    player.rotate(false);
                player.move(board, Direction.EAST, 1, players);
                fromConveyor = true;
                break;

        }
    }

    /**
     * If player is on a gear it rotates the player 90 degrees in the direction of the gear
     *  @param board  The current Board which holds all tiles
     * @param player The player who stands on the tile
     */
    private static void rotators(Board board, Player player){
        String events = getTileType(board, "OEvents", player.getPos());
        switch (events) {
            case "Floor":
                break;

            case "RotateLeft":
                player.rotate(false);
                fromConveyor = false;
                break;

            case "RotateRight":
                player.rotate(true);
                fromConveyor = false;
                break;
        }
    }

    /**
     * If player is on a flag and it is the right one, it "picks" it up
     *  @param board  The current Board which holds all tiles
     * @param player The player who stands on the tile
     */
    private static void lasers(Board board, Player player){
        String lasers = getTileType(board, "OLasers", player.getPos());
        switch (lasers) {
            case "Laser":
                player.takeDamage();
                break;

            case "Laser_2x":
                player.takeDamage();
                player.takeDamage();
                break;
        }
    }

    /**
     * If player is on a flag and it is the right one, it "picks" it up
     *  @param board  The current Board which holds all tiles
     * @param player The player who stands on the tile
     */
    private static void flags(Board board, Player player){
        String events = getTileType(board, "OEvents", player.getPos());
        switch (events) {
            case "Floor":
                break;

            case "Flag1":
                player.addFlag(1);
                player.setBackup(new Vector2(player.getPos()));
                fromConveyor = false;
                break;

            case "Flag2":
                if (player.getFlags()[0]) {
                    player.addFlag(2);
                    player.setBackup(new Vector2(player.getPos()));
                }
                fromConveyor = false;
                break;

            case "Flag3":
                if (player.getFlags()[0] && player.getFlags()[1]) {
                    player.addFlag(3);
                    player.setBackup(new Vector2(player.getPos()));
                }
                fromConveyor = false;
                break;

            case "Flag4":
                if (player.getFlags()[0] && player.getFlags()[1] && player.getFlags()[2])
                    player.addFlag(4);
                fromConveyor = false;
                break;
        }
    }

    /**
     * If player is on a flag and it is the right one, it "picks" it up
     *  @param board  The current Board which holds all tiles
     * @param player The player who stands on the tile
     */
    private static void repairs(Board board, Player player){
        String events = getTileType(board, "OEvents", player.getPos());
        switch (events) {
            case "Floor":
                break;

            case "Single_Wrench":
                if(player.getDamage() > 0)
                    player.healDamage();
                fromConveyor = false;
                break;

            case "Hammer_Wrench":
                //Also need to give an option card
                if(player.getDamage() > 0) {
                    player.healDamage();
                }
                fromConveyor = false;
                break;
        }
    }

    /**
     * @param player The player who stands on the tile
     * @return true/false if player is outside the board
     */
    public static boolean outOfBounds(Player player) {
        if (player.getPos().x < 0 || player.getPos().y < 0)
            return true;
        return (player.getPos().x >= (float) (Main.WIDTH / TILE_SIZE)) || (player.getPos().y >= (float) (Main.HEIGHT / TILE_SIZE));
    }

    /**
     * Checks if the player can move / be pushed in a certain direction.
     * E.g. false if player wants to move north but there is a wall there.
     * TODO add support for moving across multiple tiles.
     *
     * @param board  The current Board which holds all tiles
     * @param player Representing player, with it's direction
     * @param players The other robots in the game
     * @return A boolean true if you can go in a specific direction
     */
    public static boolean canGo(Board board, Player player, Direction dir, int steps, ArrayList<Player> players) {
        // Getting position of player
        Vector2 nextPos;
        if (dir == Direction.NORTH)
            nextPos = new Vector2(player.getPos().x, player.getPos().y + steps);
        else if (dir == Direction.SOUTH)
            nextPos = new Vector2(player.getPos().x, player.getPos().y - steps);
        else if (dir == Direction.EAST)
            nextPos = new Vector2(player.getPos().x + steps, player.getPos().y);
        else
            nextPos = new Vector2(player.getPos().x - steps, player.getPos().y);

        //Can push robot
        if (!(pushPlayer(board, dir, players, (int) nextPos.x, (int) nextPos.y)))
            return false;

        return canGoFromTile(board, player, dir) && canGoToTile(board, dir, nextPos);
    }

    /**
     * Checks if the player can move / be pushed in a certain direction from the current tile.
     *
     * @param board  The current Board which holds all tiles
     * @param player Representing player, with it's direction
     * @return A boolean true if you can go in a specific direction
     */
    public static boolean canGoFromTile(Board board, Player player, Direction dir) {
        String wallType = getTileType(board, "OWalls", player.getPos());
        if (wallType != null) {
            switch (wallType) {
                case "Wall_North":
                    if (dir == Direction.NORTH)
                        return false;
                    break;

                case "Wall_South":
                    if (dir == Direction.SOUTH)
                        return false;
                    break;

                case "Wall_East":
                    if (dir == Direction.EAST)
                        return false;
                    break;

                case "Wall_West":
                    if (dir == Direction.WEST)
                        return false;
                    break;
            }
        }
        return true;
    }

    /**
     * Checks if the player can move / be pushed to a current tile.
     *
     * @param board  The current Board which holds all tiles
     * @return A boolean true if you can go in a specific direction
     */
    public static boolean canGoToTile(Board board, Direction dir, Vector2 nextPos) {
        String wallType = getTileType(board, "OWalls", nextPos);
        if (wallType != null) {
            switch (wallType) {
                case "Wall_North":
                    if (dir == Direction.SOUTH)
                        return false;
                    break;

                case "Wall_South":
                    if (dir == Direction.NORTH)
                        return false;
                    break;

                case "Wall_East":
                    if (dir == Direction.WEST)
                        return false;
                    break;

                case "Wall_West":
                    if (dir == Direction.EAST)
                        return false;
                    break;
            }
        }
        return true;
    }

    /**
     * Gets tile-type at a certain position.
     *
     * @param board The Board which holds the tiles
     * @param pos   Position of the cell
     * @return A String representing the type of tile at the pos.
     */
    public static String getTileType(Board board, String layer, Vector2 pos) {
        for (MapObject mo : board.getObjectLayer(layer)) {
            if (mo instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) mo).getRectangle();
                int rectX = (int) rect.x / TILE_SIZE;
                int rectY = (int) rect.y / TILE_SIZE;

                if (pos.x == rectX && pos.y == rectY) {
                    return (String) mo.getProperties().get("type");
                }
            }
        }

        return "";
    }

    /**
     *
     * @param board The current board
     * @param dir The direction of the player that is moving
     * @param players The other robots in the game
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true/false if the player can move or not
     * True if there is no robot to push or if it is ok to push robot.
     * False if the player is trying to push the robot through a wall
     */
    private static boolean pushPlayer(Board board, Direction dir, ArrayList<Player> players, int x, int y){
        for (Player player : players){
            if(board.getPlayerLayer().getCell(x,y) != null){
                if (board.getPlayerLayer().getCell(x,y).getTile().getId() == player.getID()){
                    if (canGo(board, player, dir, 1, players)){
                        player.move(board, dir, 1, players);
                        return true;
                    }
                    return false;
                }
            }


        }
        return true;
    }
}
