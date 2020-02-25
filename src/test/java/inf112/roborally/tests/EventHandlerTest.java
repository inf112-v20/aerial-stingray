package inf112.roborally.tests;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import inf112.roborally.entities.Player;
import inf112.roborally.ui.Board;
import org.junit.Before;
import org.junit.Test;

import static inf112.roborally.events.EventHandler.playerInsideRectangle;
import static inf112.roborally.events.EventHandler.pointInsideRectangle;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventHandlerTest {

    /**
     * Rectangle
     */
    private Rectangle rect;

    /**
     * Point(s)
     */
    private Vector2 validPoint;
    private Vector2 invalidPoint;  // Outside rect

    /**
     * Player
     */
    private Player validPlayer;
    private Player invalidPlayer;  // Outside rect

    @Before
    public void initialize() {
        rect = new Rectangle(5 * Board.TILE_SIZE, 2 * Board.TILE_SIZE, 5 * Board.TILE_SIZE, 5 * Board.TILE_SIZE);

        validPlayer = new Player(new Vector2(6, 0));
        validPoint = new Vector2(5, 1);
        invalidPlayer = new Player(new Vector2(4, 5));
        invalidPoint = new Vector2(new Vector2(6, -5));
    }

    @Test
    public void testPlayerInsideRectangle() {
        assertTrue(playerInsideRectangle(validPlayer, rect));
    }

    @Test
    public void testPointInsideRectangle() {
        assertTrue(pointInsideRectangle(validPoint, rect));
    }

    @Test
    public void testPlayerNotInsideRectangle() {
        assertFalse(playerInsideRectangle(invalidPlayer, rect));
    }

    @Test
    public void testPointNotInsideRectangle() {
        assertFalse(pointInsideRectangle(invalidPoint, rect));
    }
}
