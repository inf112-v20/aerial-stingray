package inf112.roborally.tests;

import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Directions;
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
        player = new Player(playerPos);
    }

    @Test
    public void testGetPos() {
        assertEquals(player.getPos(), playerPos);
    }

    @Test
    public void testStartingDirectionNorth() {
        assertEquals(this.player.getDir(), Directions.NORTH);
    }

    @Test
    public void testRotateRight() {
        Player player = new Player(new Vector2(0, 0));
        player.rotate(true);

        assertEquals(player.getDir(), Directions.EAST);
    }

    @Test
    public void testRotateLeft() {
        Player player = new Player(new Vector2(0, 0));
        player.rotate(false);

        assertEquals(player.getDir(), Directions.WEST);
    }

    @Test
    public void testHasAllFlagsIsFalse() {
        // Should not have all flags without collecting flags
        assertFalse(player.hasAllFlags());
    }

    @Test
    public void testHasAllFlagsIsTrue() {
        Player localPlayer = new Player(new Vector2(0, 0));
        localPlayer.addFlag(1);
        localPlayer.addFlag(2);
        localPlayer.addFlag(3);
        localPlayer.addFlag(4);

        assertTrue(localPlayer.hasAllFlags());
    }

    @Test
    public void testMove1Step() {
        Player localPlayer = new Player(new Vector2(0, 0));
        localPlayer.move(1);

        assertTrue(localPlayer.getPos().x == 0 && localPlayer.getPos().y == 1);
    }
}
