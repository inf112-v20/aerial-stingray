package inf112.roborally.tests;

import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

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
    public void testHasAllFlagsIsFalse() {
        // Should not have all flags without collecting flags
        assertFalse(player.hasAllFlags());
    }

    @Test
    public void testHasAllFlagsIsTrue() {
        Player localPlayer = new Player(new Vector2());  // new player as we are gathering flags
        localPlayer.addFlag(1);
        localPlayer.addFlag(2);
        localPlayer.addFlag(3);
        localPlayer.addFlag(4);

        assertTrue(localPlayer.hasAllFlags());
    }
}
