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
    private Vector2 alternativePos;
    private Player player;

    @Before
    public void initialize() {
        playerPos = new Vector2(0, 0);
        alternativePos = new Vector2(10, 10);
        player = new Player(playerPos, Color.GREEN, 0);
    }

    @Test
    public void playerColorIsGreen() {
        assertEquals(Color.GREEN, player.color);
    }

    @Test
    public void testGetAndSetPos() {
        assertEquals(player.getPos(), playerPos);
        player.setPos(alternativePos);
        assertEquals(player.getPos(), alternativePos);
    }

    @Test
    public void testOppositeDir() {
        player.setDir(Direction.NORTH);
        assertEquals(Direction.SOUTH, player.getOppositeDir());
        assertNotEquals(Direction.NORTH, player.getOppositeDir());

        player.rotate(true);
        assertEquals(Direction.WEST, player.getOppositeDir());
        assertNotEquals(Direction.EAST, player.getOppositeDir());

        player.rotate(true);
        assertEquals(Direction.NORTH, player.getOppositeDir());
        assertNotEquals(Direction.WEST, player.getOppositeDir());

        player.rotate(true);
        assertEquals(Direction.EAST, player.getOppositeDir());
        assertNotEquals(Direction.SOUTH, player.getOppositeDir());
    }

    @Test
    public void testRotateLeft() {
        player.setDir(Direction.NORTH);
        player.rotate(false);

        assertEquals(Direction.WEST, player.getDir());
    }

    @Test
    public void testRotateRight() {
        player.setDir(Direction.NORTH);
        player.rotate(true);

        assertEquals(Direction.EAST, player.getDir());
    }

    @Test
    public void testRotate180Degree() {
        player.setDir(Direction.NORTH);
        player.rotate180();
        assertEquals(Direction.SOUTH, player.getDir());

        player.rotate180();
        assertEquals(Direction.NORTH, player.getDir());
    }

    @Test
    public void testAddAndGetFlag() {
        player.addFlag(1);
        assertTrue(player.getFlags()[0]);
        assertFalse(player.getFlags()[1]);

        player.addFlag(2);
        assertTrue(player.getFlags()[1]);
        assertFalse(player.getFlags()[2]);

        player.addFlag(3);
        assertTrue(player.getFlags()[2]);
        assertFalse(player.getFlags()[3]);

        player.addFlag(4);
        assertTrue(player.getFlags()[3]);
    }

    @Test
    public void testSetAndGetDir() {
        player.setDir(Direction.EAST);
        assertEquals(Direction.EAST, player.getDir());
    }


    @Test
    public void testStartingDirectionNorth() {
        assertEquals(player.getDir(), Direction.NORTH);
    }

    @Test
    public void testHasAllFlagsIsTrue() {
        player.addFlag(1);
        player.addFlag(2);
        player.addFlag(3);
        player.addFlag(4);

        assertTrue(player.hasAllFlags());
    }

    @Test
    public void testRespawn(){
        player.setPos(alternativePos);
        player.respawn();
        assertEquals(player.getPos(), playerPos); //since backup (for now) is starting point.
        assertNotEquals(player.getPos(),alternativePos);
    }

    @Test
    public void testSubtractLifeAndShowStatus(){
        player.subtractLife();
        assertEquals(player.showStatus(), "Life: 2, Damage: 0");
        player.subtractLife();
        assertEquals(player.showStatus(), "Life: 1, Damage: 0");
    }
}
