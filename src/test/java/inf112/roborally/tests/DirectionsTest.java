package inf112.roborally.tests;

import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Color;
import inf112.roborally.entities.Direction;
import inf112.roborally.entities.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DirectionsTest {
    private Player player;
    private Vector2 playerPos;


    @Before
    public void setUp(){

        playerPos = new Vector2(0,0);
        player = new Player(playerPos, Color.GREEN);
    }
    @Test
    public void test4Directions() {
        assertEquals(4, Direction.values().length);
    }

    @Test
    public void testSetAndGetDir(){

        player.setDir(Direction.EAST);
        assertEquals(Direction.EAST, player.getDir());
    }


    @Test
    public void testStartingDirectionNorth() {
        assertEquals(player.getDir(), Direction.NORTH);
    }


    @Test
    public void testRotateRight() {
        player.rotate(true);

        assertEquals(player.getDir(), Direction.EAST);
    }

    @Test
    public void testRotateLeft() {
        player.rotate(false);

        assertEquals(player.getDir(), Direction.WEST);
    }


    @Test
    public void testOppositeDir() {
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
    public void testRotate180Degree(){
        player.rotate180();
        assertEquals(Direction.SOUTH, player.getDir());

        player.rotate180();
        assertEquals(Direction.NORTH, player.getDir());
    }


}
