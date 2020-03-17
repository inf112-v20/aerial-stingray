package inf112.roborally.tests;

import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Color;
import inf112.roborally.entities.Direction;
import inf112.roborally.entities.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    /**
     * Used for cases where new player is not necessary -> saving lines of code
     */
    private Vector2 playerPos;
    private Player player;

    @Before
    public void initialize() {
        playerPos = new Vector2(10, 10);
        player = new Player(playerPos, Color.GREEN);
    }

    @Test
    public void playerColorIsGreen(){
        assertEquals(Color.GREEN, player.color);
    }

    @Test
    public void testGetPos() {
        assertEquals(player.getPos(), playerPos);
    }

    @Test
    public void testStartingDirectionNorth() {
        assertEquals(this.player.getDir(), Direction.NORTH);
    }

    @Test
    public void testRotateRight() {
        Player player = new Player(new Vector2(0, 0), null);
        player.rotate(true);

        assertEquals(player.getDir(), Direction.EAST);
    }

    @Test
    public void testRotateLeft() {
        Player player = new Player(new Vector2(0, 0), null);
        player.rotate(false);

        assertEquals(player.getDir(), Direction.WEST);
    }

    @Test
    public void testHasAllFlagsIsFalse() {
        // Should not have all flags without collecting flags
        assertFalse(player.hasAllFlags());
    }

    @Test
    public void testHasAllFlagsIsTrue() {
        Player localPlayer = new Player(new Vector2(0, 0), null);
        localPlayer.addFlag(1);
        localPlayer.addFlag(2);
        localPlayer.addFlag(3);
        localPlayer.addFlag(4);

        assertTrue(localPlayer.hasAllFlags());
    }

    /* Not working as it needs libGDX engine to load assets in Board.java.
    @Test
    public void testMove1Step() {
        Board board = new Board();
        Player localPlayer = new Player(new Vector2(0, 0));
        Vector2 finalPos = new Vector2(0, 1);

        localPlayer.move(board, Directions.NORTH, 1);
        assertEquals(localPlayer.getPos(), finalPos);
    }
    */
}
