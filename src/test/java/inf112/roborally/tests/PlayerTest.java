package inf112.roborally.tests;

import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    public void testHasAllFlags() {
        // Should not have all flags at constructor
        assertFalse(player.hasAllFlags());
    }
}
